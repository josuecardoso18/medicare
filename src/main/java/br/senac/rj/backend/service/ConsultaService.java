package br.senac.rj.backend.service;

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
            		.build();
        }
        return Response.ok(ConsultaSalva).build();
    }
    
    public Response buscar(Long id) {
    	Consulta ConsultaObtida = dao.buscarPorId(id);
        if (ConsultaObtida == null) {
            return Response.status(Response.Status.NOT_FOUND)
            		.entity("{\"erro\":\"Consulta não encontrada.\"}")
            		.build();
        }
        return Response.ok(ConsultaObtida).build();
    }
}