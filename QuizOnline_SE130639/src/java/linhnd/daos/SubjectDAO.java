/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import linhnd.dtos.Question;
import linhnd.dtos.Subject;

/**
 *
 * @author PC
 */
public class SubjectDAO implements Serializable {

    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SubjectDAO.class);

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizOnline_SE130639PU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.fatal(" persist : " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Subject> getSubjectForTest() {
        List<Subject> listSubjectInvalid = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Subject s ");
            List<Subject> listSubject = query.getResultList();
            for (Subject subject : listSubject) {
                Query queryCount = em.createQuery("SELECT q FROM Question q WHERE q.subjectId.subjectId = :subjectId ");
                queryCount.setParameter("subjectId", subject.getSubjectId());
                List<Question> listResult = queryCount.getResultList();
                int numberQuestionInData = listResult.size();
                if (subject.getNumberOfQuestions() < numberQuestionInData) {
                    listSubjectInvalid.add(subject);
                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.fatal(" getSubjectForTest : " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listSubjectInvalid;
    }

    public List<Subject> getSubject() {
        List<Subject> listSubject = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Subject s ");
            listSubject = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.fatal(" getSubject : " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listSubject;
    }

    public Subject getSubjectById(String subjectId) {
        EntityManager em = emf.createEntityManager();
        Subject subject = null;
        try {
            em.getTransaction().begin();
            subject = em.find(Subject.class, subjectId);
            em.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.fatal(" getSubjectById : " + e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return subject;
    }

}
