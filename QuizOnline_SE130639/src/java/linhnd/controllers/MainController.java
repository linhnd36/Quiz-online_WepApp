/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(MainController.class);

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogOutController";
    private static final String INPUSTQUESTION = "InsertQuestionController";
    private static final String REGISTRATION = "RegistrationController";
    private static final String SEARCHQUESTION = "SearchQuestionController";
    private static final String DELETECONTROLLER = "DeleteQuestionController";
    private static final String GETDETAILQUESTION = "GetDetailQuestionController";
    private static final String UPDATEQUESTIONCONTROLLER = "UpdateQuestionController";
    private static final String TESTCONTROLLER = "MakeTestController";
    private static final String FINISHTESTCONTROLLER = "FinishTestController";
    private static final String STUDENTCONTROLLER = "StudentController";
    private static final String STUDENTSEARCHCONTROLLER = "StudentSearchController";
    private static final String GETDETAILTEST = "GetDetailTestController";
    private static final String AUTHENTICATION_CONTROLLER = "AuthenticateAccountController";
    private static final String RESTORE_QUESTION_CONTROLLER = "RestoreQuestionController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            }
            if (action.equals("Logout")) {
                url = LOGOUT;
            }
            if (action.equals("InpustQuestion")) {
                url = INPUSTQUESTION;
            }
            if (action.equals("Registration")) {
                url = REGISTRATION;
            }
            if (action.equals("Search")) {
                url = SEARCHQUESTION;
            }
            if (action.equals("btnDelete")) {
                url = DELETECONTROLLER;
            }
            if (action.equals("btnDetail")) {
                url = GETDETAILQUESTION;
            }
            if (action.equals("updateQuestion")) {
                url = UPDATEQUESTIONCONTROLLER;
            }
            if (action.equals("btnStartQuiz")) {
                url = TESTCONTROLLER;
            }
            if (action.equals("testFinish")) {
                url = FINISHTESTCONTROLLER;
            }
            if (action.equals("anotherTest")) {
                url = STUDENTCONTROLLER;
            }
            if (action.equals("searchStudent")) {
                url = STUDENTSEARCHCONTROLLER;
            }
            if (action.equals("showDetailTest")) {
                url = GETDETAILTEST;
            }
            if (action.equals("Anthentication")) {
                url = AUTHENTICATION_CONTROLLER;
            }
            if (action.equals("btnRestore")) {
                url = RESTORE_QUESTION_CONTROLLER;
            } else {
                request.setAttribute("ERROR", "Your action is invalid!");
            }
        } catch (Exception e) {
            LOGGER.fatal(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
