package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.AlunoDao;
import br.senac.rj.backend.entity.Aluno;

import jakarta.ws.rs.core.Response;

/**
 * 
 * @author reinaldo.jose
 * Classe que tem a função de centralizar a lógica de negócio relacionada à entidade Aluno.
 */
public class AlunoService {
    private final AlunoDao dao = new AlunoDao();

    public Response salvar(Aluno aluno) {
        Aluno alunoSalvo = dao.salvar(aluno);
        if (alunoSalvo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
            		.entity("{\"erro\":\"Não foi possível salvar o aluno.\"}")
            		.build();
        }
        return Response.ok(alunoSalvo).build();
    }

    public Response buscar(Long id) {
        Aluno AlunoObtido = dao.buscarPorId(id);
        if (AlunoObtido == null) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Aluno não encontrado.\"}")
            		.build();
        }
        return Response.ok(AlunoObtido).build();
    }
}
