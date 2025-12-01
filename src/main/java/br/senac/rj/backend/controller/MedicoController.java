package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Medico;
import br.senac.rj.backend.service.MedicoService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author reinaldo.jose
 * Classe que é um controller REST (recurso JAX-RS) da sua aplicação, responsável por expor endpoints HTTP relacionados à entidade Aluno.
 */
@Path("/medico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicoController {
    private final MedicoService service = new MedicoService();

    @POST
    @Path("/salvar")
    public Response salvar(Medico a) {
        return service.salvar(a);
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        return service.buscar(id);
    }
}