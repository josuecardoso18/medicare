package br.senac.rj.backend.dao;

import br.senac.rj.backend.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaDao {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("backendPU2");

    public Consulta salvar(Consulta consulta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Consulta salvo = em.merge(consulta);
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

    public Consulta buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }

    public List<Consulta> listarPorPacienteEmail(String pacienteEmail) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Consulta> q = em.createQuery(
                "select c from Consulta c where c.paciente.email = :email order by c.dataHora", Consulta.class);
            q.setParameter("email", pacienteEmail);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Consulta> listarPorMedicoId(Long medicoId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Consulta> q = em.createQuery(
                "select c from Consulta c where c.medico.idMedico = :id order by c.dataHora", Consulta.class);
            q.setParameter("id", medicoId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void excluirPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Consulta c = em.find(Consulta.class, id);
            if (c != null) em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Verifica se existe conflito de horário para o mesmo médico exatamente no mesmo instante.
     * Retorna true se já existir consulta do medico na mesma dataHora.
     */
    public boolean existeConflitoHorario(Long medicoId, LocalDateTime dataHora) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> q = em.createQuery(
                "select count(c) from Consulta c where c.medico.idMedico = :id and c.dataHora = :dt", Long.class);
            q.setParameter("id", medicoId);
            q.setParameter("dt", dataHora);
            Long count = q.getSingleResult();
            return count != null && count > 0;
        } finally {
            em.close();
        }
    }
}
