package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.TurmaDao;
import br.senac.rj.backend.entity.Turma;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author reinaldo.jose
 * Classe que tem a função de centralizar a lógica de negócio relacionada à entidade Turma.
 */
public class TurmaService {
    private final TurmaDao dao = new TurmaDao();

    public Response salvar(Turma t) {
        Turma TurmaSalva = dao.salvar(t);
        if (TurmaSalva == null) {
            return Response.status(Response.Status.BAD_REQUEST)
            		.entity("{\"erro\":\"Não foi possível salvar a turma.\"}")
            		.build();
        }
        return Response.ok(TurmaSalva).build();
    }
    
    public Response buscar(Long id) {
    	Turma TurmaObtida = dao.buscarPorId(id);
        if (TurmaObtida == null) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Turma não encontrada.\"}")
            		.build();
        }
        return Response.ok(TurmaObtida).build();
    }
}
