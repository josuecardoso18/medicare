package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.MedicoDao;
import br.senac.rj.backend.entity.Medico;

import jakarta.ws.rs.core.Response;

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
}