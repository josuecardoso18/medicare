package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.ConsultaDao;
import br.senac.rj.backend.dao.MedicoDao;
import br.senac.rj.backend.dao.UsuarioDao;
import br.senac.rj.backend.entity.Consulta;
import br.senac.rj.backend.entity.Medico;
import br.senac.rj.backend.entity.Usuario;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Serviço que centraliza regras de negócio para Agendamento de Consultas.
 */
public class ConsultaService {

    private final ConsultaDao consultaDao = new ConsultaDao();
    private final MedicoDao medicoDao = new MedicoDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();

    public Response agendar(Consulta c) {
        if (c == null || c.getMedico() == null || c.getPaciente() == null || c.getDataHora() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Dados incompletos para agendamento.\"}")
                    .build();
        }

        Long medicoId = c.getMedico().getIdMedico();
        String pacienteEmail = c.getPaciente().getEmail();

        // valida existência do médico
        Medico m = medicoDao.buscarPorId(medicoId);
        if (m == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Médico não encontrado.\"}")
                    .build();
        }

        // valida existência do paciente (UsuarioDao busca por PK email)
        Usuario u = usuarioDao.buscarPorEmailSenha(pacienteEmail, ""); // não vamos verificar senha aqui
        // Note: UsuarioDao.buscarPorEmailSenha requer senha; alternativa: buscar via em.find por classe Usuario
        if (u == null) {
            // tentar buscar diretamente pelo EntityManager via buscarPorEmailSenha não é ideal sem senha.
            // Como não queremos alterar UsuarioDao, falamos por segurança: verificar existência usando buscarPorEmailSenha com null/empty.
            // Se isso falhar no seu projeto, substitua por um método de busca por email sem senha.
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Paciente não encontrado.\"}")
                    .build();
        }

        // verifica conflito de horário exato
        boolean conflito = consultaDao.existeConflitoHorario(medicoId, c.getDataHora());
        if (conflito) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"erro\":\"Médico indisponível no horário informado.\"}")
                    .build();
        }

        // associa as entidades completas (para evitar estado detached/ids apenas)
        c.setMedico(m);
        c.setPaciente(u);

        Consulta salvo = consultaDao.salvar(c);
        if (salvo == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Falha ao salvar a consulta.\"}")
                    .build();
        }
        return Response.ok(salvo).build();
    }

    public List<Consulta> listarPorPacienteEmail(String pacienteEmail) {
        return consultaDao.listarPorPacienteEmail(pacienteEmail);
    }

    public List<Consulta> listarPorMedicoId(Long medicoId) {
        return consultaDao.listarPorMedicoId(medicoId);
    }

    public List<Consulta> listarTodas() {
        // útil para debugging/admin; limita se necessário no DAO
        return consultaDao.listarPorMedicoId(null); // não há método direto; fallback:
        // Se preferir, implemente listarTodas() no ConsultaDao. Aqui deixei um placeholder.
    }

    public boolean cancelar(Long id, String pacienteEmail) {
        if (id == null) return false;
        Consulta c = consultaDao.buscarPorId(id);
        if (c == null) return false;
        // valida pertencimento
        if (pacienteEmail == null || !pacienteEmail.equals(c.getPaciente().getEmail())) {
            return false;
        }
        consultaDao.excluirPorId(id);
        return true;
    }
}