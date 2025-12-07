package br.senac.rj.backend.dao;

import java.util.List;

import br.senac.rj.backend.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
//	public Medico buscarPorNome(String nome) {
//		EntityManager em = emf.createEntityManager();
//		try {
//			return em.find(Medico.class, nome);
//		} finally {
//			em.close();
//		}
//	}
//	public Medico buscarPorEsp(String especialidade) {
//		EntityManager em = emf.createEntityManager();
//		try {
//			return em.find(Medico.class, especialidade);
//		} finally {
//			em.close();
//		}
//	}
//	public Medico buscarPorEnd(String endereco) {
//		EntityManager em = emf.createEntityManager();
//		try {
//			return em.find(Medico.class, endereco);
//		} finally {
//			em.close();
//		}
//	}
	
	public List<Medico> buscarPorNome(String nome) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.createQuery("SELECT m FROM Medico m WHERE m.nome = :nome", Medico.class)
	                 .setParameter("nome", nome)
	                 .getResultList();
	    } finally {
	        em.close();
	    }
	}

	public List<Medico> buscarPorEspecialidade(String especialidade) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.createQuery("SELECT m FROM Medico m WHERE m.especialidade = :esp", Medico.class)
	                 .setParameter("esp", especialidade)
	                 .getResultList();
	    } finally {
	        em.close();
	    }
	}

	public List<Medico> buscarPorEndereco(String endereco) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.createQuery("SELECT m FROM Medico m WHERE m.endereco = :end", Medico.class)
	                 .setParameter("end", endereco)
	                 .getResultList();
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
