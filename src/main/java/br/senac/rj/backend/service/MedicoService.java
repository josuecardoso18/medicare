package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.MedicoDao;
import br.senac.rj.backend.entity.Medico;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * 
 * @author reinaldo.jose
 * Classe que tem a função de centralizar a lógica de negócio relacionada à entidade Aluno.
 */
public class MedicoService {
    private final MedicoDao dao = new MedicoDao();

    public Response salvar(Medico medico) {
        Medico medicoSalvo = dao.salvar(medico);
        if (medicoSalvo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
            		.entity("{\"erro\":\"Não foi possível salvar o médico.\"}")
            		.build();
        }
        return Response.ok(medicoSalvo).build();
    }

    public Response buscar(Long id) {
        Medico MedicoObtido = dao.buscarPorId(id);
        if (MedicoObtido == null) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Médico não encontrado.\"}")
            		.build();
        }
        return Response.ok(MedicoObtido).build();
    }
    
    public Response buscarPorNome(String nome) {
        List<Medico> medicos = (List<Medico>) dao.buscarPorNome(nome);
        if (medicos == null || medicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Nenhum médico encontrado com esse nome.\"}")
            		.build();
        }
        return Response.ok(medicos).build();
    }
    
    public Response buscarPorEsp(String especialidade) {
        List<Medico> medicos = (List<Medico>) dao.buscarPorEsp(especialidade);
        if (medicos == null || medicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Nenhum médico encontrado para essa especialidade.\"}")
            		.build();
        }
        return Response.ok(medicos).build();
    }
    
    public Response buscarPorEnd(String endereco) {
        List<Medico> medicos = (List<Medico>) dao.buscarPorEnd(endereco);
        if (medicos == null || medicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Nenhum médico encontrado para esse endereço.\"}")
            		.build();
        }
        return Response.ok(medicos).build();
    }
    
    public Response deletar(Long id) {
        try {
            boolean removed = dao.deletarPorId(id);
            if (!removed) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"erro\":\"Médico não encontrado.\"}")
                               .build();
            }
            return Response.noContent().build(); // 204
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"erro\":\"Erro ao excluir médico.\"}")
                           .build();
        }
    }

}