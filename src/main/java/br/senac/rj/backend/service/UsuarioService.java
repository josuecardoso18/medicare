package br.senac.rj.backend.service;

import br.senac.rj.backend.dao.UsuarioDao;
import br.senac.rj.backend.entity.Usuario;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author reinaldo.jose
 * Classe que tem a função de centralizar a lógica de negócio relacionada à entidade Usuario.
 */
public class UsuarioService {
    private final UsuarioDao dao = new UsuarioDao();
    private final AuthService authService = new AuthService();

    public Response salvar(Usuario usuario) {
        Usuario UsuarioSalvo = dao.salvar(usuario);
        if (UsuarioSalvo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
            		.entity("{\"erro\":\"Não foi possível salvar o usuário.\"}")
            		.build();
        }
        return Response.ok(UsuarioSalvo).build();
    }

    public Response login(String email, String senha) {
        Usuario usuario = dao.buscarPorEmailSenha(email, senha);
        if (usuario == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
            		.entity("{\"erro\":\"Dados incorretos.\"}")
            		.build();
        }
        String token = authService.gerarToken(email);
        return Response.ok(token).build();
    }

}
