/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhnd.daos.QuestionDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "SearchQuestionController", urlPatterns = {"/SearchQuestionController"})
public class SearchQuestionController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(SearchQuestionController.class);

    private final static int PAGE_SIZE = 20;
    private static final String LOADPAGECONTROLLER = "LoadPageSearchController";
    private static final String ERROR = "error.jsp";
    private static final String SEARCHCONTROLLER = "PageSearchController";

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
            QuestionDAO dao = new QuestionDAO();

            String txtSearch = request.getParameter("txtSearchQuestion");
            String subjectId = request.getParameter("subjectId");
            String statusQuestion = request.getParameter("statusQuestion");

            if (!txtSearch.trim().equals("")) {
                int numberOfQuestion = dao.countQuestionSearch(txtSearch, subjectId, statusQuestion);
                int page = (int) Math.ceil((double) numberOfQuestion / PAGE_SIZE);
                session.setAttribute("PAGE", page);
                session.setAttribute("SEARCH_VALUE", txtSearch);
                session.setAttribute("SUBJECT_VALUE", subjectId);
                session.setAttribute("STATUS_QUESTION", statusQuestion);
                url = LOADPAGECONTROLLER;
            } else {
                url = SEARCHCONTROLLER;
            }

        } catch (Exception ex) {
            LOGGER.fatal(ex);
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
