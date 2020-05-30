/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.daos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import linhnd.dtos.Answer;
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

    public int countQuestionSearch(String txtSearch, String subjectId, String statusQuestion) {
        EntityManager em = emf.createEntityManager();
        int countQuestion = 0;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT q FROM Question q, Subject s WHERE q.subjectId.subjectId = s.subjectId AND q.questionContent LIKE :textSearch AND q.subjectId.subjectId = :subjectId AND q.statusId.statusId = :statusQUestion");
            query.setParameter("textSearch", "%" + txtSearch + "%");
            query.setParameter("subjectId", subjectId);
            query.setParameter("statusQUestion", statusQuestion);
            countQuestion = (int) query.getResultList().size();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return countQuestion;
    }

    public List<Question> subListQuestionSearch(String txtSearch, String subjectId, String statusQuestion, int startIndex, int pageSize) {
        EntityManager em = emf.createEntityManager();
        List<Question> listQuestionSearch = null;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT q FROM Question q, Subject s WHERE q.subjectId.subjectId = s.subjectId "
                    + "AND q.questionContent LIKE :textSearch AND q.subjectId.subjectId = :subjectId AND q.statusId.statusId = :statusQUestion");
            query.setParameter("textSearch", "%" + txtSearch + "%");
            query.setParameter("subjectId", subjectId);
            query.setParameter("statusQUestion", statusQuestion);
            query.setFirstResult(startIndex).setMaxResults(pageSize);
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

    public boolean deleteQuestion(int questionId) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            em.getTransaction().begin();
            Question question = em.find(Question.class, questionId);
            question.setStatusId(em.find(Status.class, "QuesDelete"));
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

    public Question getQuestionById(int questionId) {
        EntityManager em = emf.createEntityManager();
        Question question = null;
        try {
            em.getTransaction().begin();
            question = em.find(Question.class, questionId);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return question;
    }

    public boolean updateQuestion(int questionId, String questionContent, String subjectId, List<Answer> lsitAnswer, int answerId) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            em.getTransaction().begin();
            Question question = em.find(Question.class, questionId);
            question.setQuestionContent(questionContent);
            question.setCorrectAnswerID(String.valueOf(answerId));
            question.setSubjectId(em.find(Subject.class, subjectId));
            question.setAnswerCollection(lsitAnswer);
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

    public LinkedHashMap<String, Question> getListQuestionRandom(int numberOfQuestion, String subjectId) {
        EntityManager em = emf.createEntityManager();
        List<Integer> listQuestionId = null;
        LinkedHashMap<String, Question> listRandomQuestion = new LinkedHashMap<>();
        int count = 1;
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("SELECT q.QuestionId FROM dbo.Question q WHERE q.SubjectId = ? AND q.StatusId = 'QuesActive' ORDER BY NEWID()");
            listQuestionId = query.setParameter(1, subjectId).setMaxResults(numberOfQuestion).getResultList();
            if (!listQuestionId.isEmpty()) {
                for (Integer questionId : listQuestionId) {
                    listRandomQuestion.put("Question " + count, em.find(Question.class, questionId));
                    count++;
                }
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listRandomQuestion;
    }

}
