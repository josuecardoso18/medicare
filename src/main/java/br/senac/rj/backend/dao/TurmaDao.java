package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Turma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TurmaDao {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("backendPU");

    public Turma salvar(Turma t) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Turma turmaSalva = em.merge(t); // obter objeto completo salvo
            em.getTransaction().commit();
            return turmaSalva;
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

    public Turma buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Turma.class, id);
        } finally {
            em.close();
        }
    }
}
