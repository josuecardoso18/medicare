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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Controller REST para agendamento e gerenciamento de consultas.
 */
@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaController {

    private final ConsultaService service = new ConsultaService();

    @POST
    public Response agendar(Consulta c) {
        Response resp = service.agendar(c);
        return resp;
    }

    @GET
    public Response listarPorPaciente(@QueryParam("pacienteEmail") String pacienteEmail,
                                      @QueryParam("medicoId") Long medicoId) {
        if (pacienteEmail != null && !pacienteEmail.isBlank()) {
            List<Consulta> l = service.listarPorPacienteEmail(pacienteEmail);
            return Response.ok(l).build();
        }
        if (medicoId != null) {
            List<Consulta> l = service.listarPorMedicoId(medicoId);
            return Response.ok(l).build();
        }
        
        List<Consulta> todas = service.listarTodas();
        return Response.ok(todas).build();
    }

    @DELETE
    @Path("/{id}")
    public Response cancelar(@PathParam("id") Long id, @QueryParam("pacienteEmail") String pacienteEmail) {
        boolean ok = service.cancelar(id, pacienteEmail);
        if (ok) return Response.noContent().build();
        return Response.status(Response.Status.FORBIDDEN).entity("Não autorizado ou não encontrado").build();
    }
}
