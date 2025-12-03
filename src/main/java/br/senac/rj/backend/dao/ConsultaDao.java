package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConsultaDao {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("backendPU2");

    public Consulta salvar(Consulta consulta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Consulta ConsultaSalva = em.merge(consulta); // obter objeto completo salvo
            em.getTransaction().commit();
            return ConsultaSalva;
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

    public Consulta buscarPorId(Long idConsulta) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Consulta.class, idConsulta);
        } finally {
            em.close();
        }
    }
    
    public boolean deletarPorId(Long idConsulta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Consulta c = em.find(Consulta.class, idConsulta);
            if (c == null) {
                em.getTransaction().rollback();
                return false;
            }
            em.remove(c);
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
