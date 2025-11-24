package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.ConvenioDao;
import br.senac.rj.backend.entity.Convenio;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Serviço com lógica mínima para Convênio.
 */
public class ConvenioService {

    private final ConvenioDao dao = new ConvenioDao();

    public List<Convenio> listarTodos() {
        return dao.listarTodos();
    }

    public Convenio buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public Response salvar(Convenio convenio) {
        Convenio salvo = dao.salvar(convenio);
        if (salvo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível salvar o convênio.\"}")
                    .build();
        }
        return Response.ok(salvo).build();
    }
}