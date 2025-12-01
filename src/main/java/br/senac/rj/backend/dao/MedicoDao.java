package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class MedicoDao {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU2");

	public Medico salvar(Medico medico) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Medico MedicoSalvo = em.merge(medico); // obter objeto completo salvo
			em.getTransaction().commit();
			return MedicoSalvo;
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

	public Medico buscarPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Medico.class, id);
		} finally {
			em.close();
		}
	}
	public Medico buscarPorNome(String nome) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Medico.class, nome);
		} finally {
			em.close();
		}
	}
	public Medico buscarPorEsp(String especialidade) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Medico.class, especialidade);
		} finally {
			em.close();
		}
	}
	public Medico buscarPorEnd(String endereco) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Medico.class, endereco);
		} finally {
			em.close();
		}
	}
	
	public boolean deletarPorId(Long id) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();
	        Medico m = em.find(Medico.class, id);
	        if (m == null) {
	            em.getTransaction().rollback();
	            return false;
	        }
	        em.remove(m);
	        em.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) em.getTransaction().rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}

}