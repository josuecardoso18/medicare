package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Consulta;
import br.senac.rj.backend.service.ConsultaService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
 * Classe que é um controller REST (recurso JAX-RS) da sua aplicação, responsável por expor endpoints HTTP relacionados à entidade Turma.
 */
@Path("/consulta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaController {
    private final ConsultaService service = new ConsultaService();
    
    @POST
    @Path("/salvar")
    public Response salvar(Consulta t) {
        return service.salvar(t);
    }
    @GET
    @Path("/{id_consulta}")
    public Response buscar(@PathParam("id_consulta") Long id_consulta) {
        return service.buscar(id_consulta);
    }
    
    @GET
    @Path("/{id}")
    public Response buscarPorMed(@PathParam("id") Long id) {
        return service.buscarPorMed(id);
    }
    
    @DELETE
    @Path("/{id_consulta}")
    public Response deletar(@PathParam("id_consulta") Long id_consulta) {
        return service.deletar(id_consulta);
    }
    
}