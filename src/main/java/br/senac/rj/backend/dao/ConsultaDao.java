package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConsultaDao {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("backendPU2");

    public Consulta salvar(Consulta t) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Consulta consultaSalva = em.merge(t); // obter objeto completo salvo
            em.getTransaction().commit();
            return consultaSalva;
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

    public Consulta buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }
}