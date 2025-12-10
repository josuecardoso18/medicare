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
                    .type("application/json")
                    .build();
        }
        return Response.ok(medicoSalvo)
                .type("application/json")
                .build();
    }

    public Response buscar(Long id) {
        Medico MedicoObtido = dao.buscarPorId(id);
        if (MedicoObtido == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Médico não encontrado.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(MedicoObtido)
                .type("application/json")
                .build();
    }

    public Response buscarPorNome(String nome) {
        List<Medico> medicos = dao.buscarPorNome(nome);
        if (medicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Nenhum médico encontrado com esse nome.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(medicos)
                .type("application/json")
                .build();
    }

    public Response buscarPorEsp(String especialidade) {
        List<Medico> medicos = dao.buscarPorEspecialidade(especialidade);
        if (medicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Nenhum médico encontrado por essa especialidade.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(medicos)
                .type("application/json")
                .build();
    }

    public Response buscarPorEnd(String endereco) {
        List<Medico> medicos = dao.buscarPorEndereco(endereco);
        if (medicos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Nenhum médico encontrado por esse endereço.\"}")
                    .type("application/json")
                    .build();
        }
        return Response.ok(medicos)
                .type("application/json")
                .build();
    }

    public Response deletar(Long id) {
        try {
            boolean removed = dao.deletarPorId(id);
            if (!removed) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\":\"Médico não encontrado.\"}")
                        .type("application/json")
                        .build();
            }
            return Response.noContent().build(); // 204 sem corpo
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Erro ao excluir médico.\"}")
                    .type("application/json")
                    .build();
        }
    }
}
