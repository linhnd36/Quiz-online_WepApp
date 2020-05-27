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
import linhnd.dtos.Question;
import linhnd.dtos.Status;
import linhnd.dtos.Subject;

/**
 *
 * @author PC
 */
public class QuestionDAO implements Serializable {

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

    public boolean newQuestion(String questionContent, String subjectId) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            em.getTransaction().begin();
            Question question = new Question();
            question.setQuestionId(null);
            question.setCreateDate(new Timestamp(System.currentTimeMillis()));
            question.setQuestionContent(questionContent);
            question.setCorrectAnswerID("2");
            question.setSubjectId(em.find(Subject.class, subjectId));
            question.setStatusId(em.find(Status.class, "QuesActive"));
            em.persist(question);
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

    public Question getQuestionId() {
        EntityManager em = emf.createEntityManager();
        Question question = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT q FROM Question q ORDER BY q.questionId DESC");
            question = (Question) query.setMaxResults(1).getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return question;
    }

    public boolean updateCorrecAnswerForQuestion(int questionId, String correctAnswerID) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            em.getTransaction().begin();
            Question question = em.find(Question.class, questionId);
            question.setCorrectAnswerID(correctAnswerID);
            em.merge(question);
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

    public List<Question> getListQuestionSearch(String txtSearch) {
        EntityManager em = emf.createEntityManager();
        List<Question> listQuestionSearch = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT q FROM Question q, Subject s WHERE q.questionContent LIKE :textSearch OR s.subjectName LIKE :textSearchSubject");
            query.setParameter("textSearch", "%" + txtSearch + "%");
            query.setParameter("textSearchSubject", "%" + txtSearch + "%");
            listQuestionSearch = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listQuestionSearch;
    }

}
