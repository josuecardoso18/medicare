package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Convenio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ConvenioDao {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU");

    public Convenio salvar(Convenio convenio) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Convenio salvo = em.merge(convenio);
            em.getTransaction().commit();
            return salvo;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Convenio buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Convenio.class, id);
        } finally {
            em.close();
        }
    }

    public List<Convenio> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Convenio> q = em.createQuery("select c from Convenio c", Convenio.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}