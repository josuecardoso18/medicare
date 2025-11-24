package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Medico;
import br.senac.rj.backend.service.MedicoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Controller REST para operações de consulta e busca de médicos.
 */
@Path("/medicos")
@Produces(MediaType.APPLICATION_JSON)
public class MedicoController {

    private final MedicoService service = new MedicoService();

    @GET
    public Response listar(
            @QueryParam("nome") String nome,
            @QueryParam("especialidade") String especialidade,
            @QueryParam("endereco") String endereco,
            @QueryParam("convenioId") Integer convenioId) {

        List<Medico> result = service.buscar(nome, especialidade, endereco, convenioId);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Medico m = service.buscarPorId(id);
        if (m == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(m).build();
    }
}