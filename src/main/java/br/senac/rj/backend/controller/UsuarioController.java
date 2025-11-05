package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Usuario;
import br.senac.rj.backend.service.UsuarioService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author reinaldo.jose
 * Classe que é um controller REST (recurso JAX-RS) da sua aplicação, responsável por expor endpoints HTTP relacionados à entidade Usuario.
 */
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {
    private final UsuarioService service = new UsuarioService();

    @POST
    @Path("/login")
    public Response login(Usuario u) {
        return service.login(u.getEmail(), u.getSenha());
    }

    @POST
    @Path("/salvar")
    public Response salvar(Usuario u) {
        return service.salvar(u);
    }
}
