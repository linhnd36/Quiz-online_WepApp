/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.daos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import linhnd.dtos.Test;
import linhnd.dtos.TestQuestions;

/**
 *
 * @author PC
 */
public class TestDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizOnline_SE130639PU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean newTest(String testTitle, float score, String email, List<TestQuestions> listTestQuestion) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("INSERT INTO dbo.Test ( TestTitle, Score, CreateDate, Email ) OUTPUT Inserted.TestId VALUES  ( ?, ?, ?, ?)");
            query.setParameter(1, testTitle);
            query.setParameter(2, score);
            query.setParameter(3, new Timestamp(System.currentTimeMillis()), TemporalType.TIMESTAMP);
            query.setParameter(4, email);
            int testIdInsert = (int) query.getSingleResult();
            Test test = em.find(Test.class, testIdInsert);
            for (TestQuestions testQuestions : listTestQuestion) {
                testQuestions.setTestId(em.find(Test.class, testIdInsert));
            }
            test.setTestQuestionsCollection(listTestQuestion);
            em.merge(test);
            em.getTransaction().commit();
            check = true;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return check;
    }

    public int countSearchTest(String email, String subjectId) {
        EntityManager em = emf.createEntityManager();
        int count = 0;
        List<Test> listTestSearch = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT t FROM Test t WHERE t.email.email = :email AND t.testTitle = :subjectId");
            query.setParameter("email", email);
            query.setParameter("subjectId", subjectId);
            listTestSearch = query.getResultList();
            count = listTestSearch.size();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return count;
    }

    public List<Test> subPageSearchTest(String email, String subjectId, int start, int end) {
        EntityManager em = emf.createEntityManager();
        List<Test> listTestSearch = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT t FROM Test t WHERE t.email.email = :email AND t.testTitle = :subjectId");
            query.setParameter("email", email);
            query.setParameter("subjectId", subjectId);
            query.setFirstResult(start).setMaxResults(end);
            listTestSearch = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listTestSearch;
    }

    public Test getTestDetail(int testId) {
        EntityManager em = emf.createEntityManager();
        Test test = null;
        try {
            em.getTransaction().begin();
            test = em.find(Test.class, testId);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return test;
    }

}
