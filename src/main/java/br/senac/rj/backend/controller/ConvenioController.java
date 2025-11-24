package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Convenio;
import br.senac.rj.backend.service.ConvenioService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Controller REST para listar convênios.
 */
@Path("/convenios")
@Produces(MediaType.APPLICATION_JSON)
public class ConvenioController {

    private final ConvenioService service = new ConvenioService();

    @GET
    public Response listarTodos() {
        List<Convenio> lista = service.listarTodos();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) {
        Convenio c = service.buscarPorId(id);
        if (c == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(c).build();
    }
}
