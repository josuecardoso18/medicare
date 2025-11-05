package br.senac.rj.backend.filter;

import br.senac.rj.backend.service.AuthService;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * 
 * @author reinaldo.jose
 * Mecanismo que protege as rotas (endpoints) da sua API, garantindo que apenas requisições com um JWT válido possam acessar os recursos.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private final AuthService authService = new AuthService();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();

        // ✅ Permite acesso livre ao login e ao cadastro de usuário
        if (path.equals("usuario/login") || path.equals("usuario/salvar")) {
            return;
        }

        // 🔍 Pega o header Authorization
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            abort(requestContext, "Token ausente ou formato inválido.");
            return;
        }

        String token = authHeader.substring("Bearer".length()).trim();

        // 🔐 Valida o token com AuthService
        if (!authService.validarToken(token)) {
            abort(requestContext, "Token inválido ou expirado.");
            return;
        }

        // Opcional: Recuperar o usuário do token
        String email = authService.getEmailDoToken(token);
        requestContext.setProperty("usuarioEmail", email); // útil se quiser usar no endpoint
    }

    private void abort(ContainerRequestContext ctx, String mensagem) {
        ctx.abortWith(
            Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"erro\":\"" + mensagem + "\"}")
                    .build()
        );
    }
}
