package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Usuario;
import br.senac.rj.backend.util.PasswordUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsuarioDao {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU");

	public Usuario salvar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();

			// Gera hash da senha antes de salvar
			if (usuario.getSenha() != null) {
				usuario.setSenha(PasswordUtil.gerarHash(usuario.getSenha()));
			}

			Usuario usuarioSalvo = em.merge(usuario); // obter objeto completo salvo
			em.getTransaction().commit();
			return usuarioSalvo;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback(); // desfazer transações pendentes
			}
			e.printStackTrace(); // Para depuração
			return null;
		} finally {
			em.close();
		}
	}

	public Usuario buscarPorEmailSenha(String email, String senha) {
		EntityManager em = emf.createEntityManager();
		try {
			Usuario usuario = em.find(Usuario.class, email); // busca pelo email
			if (usuario != null && PasswordUtil.verificarSenha(senha, usuario.getSenha())) {
				return usuario; // senha correta
			}
			return null; // usuário não encontrado ou senha incorreta
		} finally {
			em.close();
		}
	}

}
