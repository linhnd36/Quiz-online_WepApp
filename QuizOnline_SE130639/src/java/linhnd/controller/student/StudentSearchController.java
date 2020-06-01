/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.student;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhnd.daos.SubjectDAO;
import linhnd.daos.TestDAO;
import linhnd.dtos.Subject;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "StudentSearchController", urlPatterns = {"/StudentSearchController"})
public class StudentSearchController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(StudentSearchController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCESS = "LoadPageHistoryController";
    private static final int PAGE_SIZE = 20;

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
            TestDAO daoTest = new TestDAO();
            SubjectDAO daoSubject = new SubjectDAO();
            Subject subject = daoSubject.getSubjectById(subjectId);
            String email = (String) session.getAttribute("EMAIL");
            int numberOfResultSearch = daoTest.countSearchTest(email, subject.getSubjectId() + "-" + subject.getSubjectName());
            int page = (int) Math.ceil((double) numberOfResultSearch / PAGE_SIZE);
            url = SUCESS;
            session.setAttribute("PAGE_SEARCH_HISTORY", page);
            session.setAttribute("SUBJECT_SEARCH", subject);
            
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
