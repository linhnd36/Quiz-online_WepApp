/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.daos;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import linhnd.dtos.Answer;
import linhnd.dtos.Question;

/**
 *
 * @author PC
 */
public class AnswerDAO implements Serializable {

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

    public boolean newAnswer(List<String> listAnswer, int questionId) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            em.getTransaction().begin();

            for (String sAnswer : listAnswer) {
                Answer answer = new Answer();
                answer.setAnswerContent(sAnswer);
                answer.setAnswerId(null);
                answer.setQuestionId(em.find(Question.class, questionId));

                Question question = em.find(Question.class, questionId);
                question.getAnswerCollection().add(answer);

                em.merge(question);
            }

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

    public int getAnswerId(String correctAnswer, int questionId) {
        EntityManager em = emf.createEntityManager();
        int result = 0;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM Answer a WHERE a.answerContent = :answerContent AND a.questionId.questionId = :questionId");
            query.setParameter("answerContent", correctAnswer);
            query.setParameter("questionId", questionId);
            Answer answer = (Answer) query.setFirstResult(0).setMaxResults(1).getSingleResult();
            result = answer.getAnswerId();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return result;
    }

    public Answer getAnswer(int answerId) {
        EntityManager em = emf.createEntityManager();
        Answer answer = null;
        try {
            em.getTransaction().begin();
            answer = em.find(Answer.class, answerId);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return answer;
    }

}
