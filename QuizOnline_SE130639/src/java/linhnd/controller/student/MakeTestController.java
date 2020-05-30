/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.student;

import java.io.IOException;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhnd.daos.QuestionDAO;
import linhnd.daos.SubjectDAO;
import linhnd.dtos.Question;
import linhnd.dtos.Subject;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "MakeTestController", urlPatterns = {"/MakeTestController"})
public class MakeTestController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(MakeTestController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCESS = "studentTest.jsp";

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
            HttpSession session = request.getSession();
            String subjectId = request.getParameter("subjectId");
            SubjectDAO daoSubject = new SubjectDAO();
            QuestionDAO daoQuestion = new QuestionDAO();
            Subject subject = daoSubject.getSubjectById(subjectId);
            LinkedHashMap<String, Question> mapRandomQuestion = daoQuestion.getListQuestionRandom(subject.getNumberOfQuestions(), subject.getSubjectId());
            session.setAttribute("MAP_QUESTION_TEST", mapRandomQuestion);
            session.setAttribute("SUBJECT_TEST", subject);
            url = SUCESS;

        } catch (Exception e) {
            LOGGER.fatal(e);
            e.printStackTrace();
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
