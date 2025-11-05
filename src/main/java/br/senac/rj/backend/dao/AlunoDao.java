package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AlunoDao {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU");

	public Aluno salvar(Aluno aluno) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Aluno AlunoSalvo = em.merge(aluno); // obter objeto completo salvo
			em.getTransaction().commit();
			return AlunoSalvo;
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

	public Aluno buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Aluno.class, id);
		} finally {
			em.close();
		}
	}
}
