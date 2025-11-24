package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MedicoDao {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU2");

    public Medico salvar(Medico medico) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Medico salvo = em.merge(medico);
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

    public Medico buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Medico.class, id);
        } finally {
            em.close();
        }
    }

    public List<Medico> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Medico> q = em.createQuery("select m from Medico m", Medico.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Medico> buscarPorNome(String nomeParcial) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Medico> q = em.createQuery(
                "select m from Medico m where lower(m.nome) like :p", Medico.class);
            q.setParameter("p", "%" + nomeParcial.toLowerCase() + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Medico> buscarPorEspecialidade(String especialidadeParcial) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Medico> q = em.createQuery(
                "select m from Medico m where lower(m.especialidade) like :p", Medico.class);
            q.setParameter("p", "%" + especialidadeParcial.toLowerCase() + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Medico> buscarPorEndereco(String enderecoParcial) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Medico> q = em.createQuery(
                "select m from Medico m where lower(m.enderecoMedico) like :p", Medico.class);
            q.setParameter("p", "%" + enderecoParcial.toLowerCase() + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Medico> buscarPorConvenioId(Integer idConvenio) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Medico> q = em.createQuery(
                "select distinct m from Medico m join m.convenios c where c.id = :id", Medico.class);
            q.setParameter("id", idConvenio);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
