package br.senac.rj.backend.service;

import java.util.List;

import br.senac.rj.backend.dao.ConsultaDao;
import br.senac.rj.backend.entity.Consulta;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author reinaldo.jose
 * Classe que tem a função de centralizar a lógica de negócio relacionada à entidade Turma.
 */
public class ConsultaService {
    private final ConsultaDao dao = new ConsultaDao();

    public Response salvar(Consulta t) {
        Consulta ConsultaSalva = dao.salvar(t);
        if (ConsultaSalva == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível salvar a consulta.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(ConsultaSalva)
                .type("application/json")
                .build();
    }

    public Response buscar(Long idConsulta) {
        Consulta ConsultaObtida = dao.buscarPorId(idConsulta);
        if (ConsultaObtida == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Consulta não encontrada.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(ConsultaObtida)
                .type("application/json")
                .build();
    }

    public Response buscarPorPaciente(String emailPaciente) {
        List<Consulta> consultas = dao.buscarPorPaciente(emailPaciente);
        if (consultas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Nenhuma consulta encontrada.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(consultas)
                .type("application/json")
                .build();
    }

    public Response deletar(Long idConsulta) {
        try {
            boolean removed = dao.deletarPorId(idConsulta);
            if (!removed) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\":\"Consulta não encontrada.\"}")
                        .type("application/json")
                        .build();
            }
            return Response.noContent().build(); // 204 sem corpo
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Erro ao excluir consulta.\"}")
                    .type("application/json")
                    .build();
        }
    }
}
