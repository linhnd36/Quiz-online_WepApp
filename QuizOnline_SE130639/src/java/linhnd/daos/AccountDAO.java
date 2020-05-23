/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.daos;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import linhnd.dtos.Account;

/**
 *
 * @author PC
 */
public class AccountDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuizOnline_SE130639PU");

    public String checkLogin(String email, String password) {
        String role = "failed";
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM Account a WHERE a.email = :email AND a.password = :password ");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Account acount = (Account) query.getSingleResult();
            role = acount.getRoleId().getRoleName();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().equals("did not retrieve any entities")) {
                return role;
            } else {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception in function checkLogin "+ e.getMessage());
            }
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return role;
    }
        public String getStatusAccount(String email) {
        String status = "failed";
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Account acount =  em.find(Account.class, email);
            status = acount.getStatusId().getStatusId();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().equals("did not retrieve any entities")) {
                return status;
            } else {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception in function checkLogin "+ e.getMessage());
            }
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return status;
    }
    

    public String getName(String email) {
        String name = "";
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Account account = em.find(Account.class, email);
            name = account.getName();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception in function getName", e.getMessage());
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return name;
    }

}
