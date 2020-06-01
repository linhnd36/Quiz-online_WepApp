/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhnd.daos.QuestionDAO;
import linhnd.dtos.Question;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoadPageSearchController", urlPatterns = {"/LoadPageSearchController"})
public class LoadPageSearchController extends HttpServlet implements Serializable {

    static Logger LOGGER = Logger.getLogger(LoadPageSearchController.class);

    private final static int PAGE_SIZE = 20;
    private final static String ERROR = "error.jsp";
    private final static String ADMIN_SEARCH_PAGE = "adminSearchPage.jsp";

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
            QuestionDAO dao = new QuestionDAO();
            HttpSession session = request.getSession();
            String txtSearch = (String) session.getAttribute("SEARCH_VALUE");
            String subjectId = (String) session.getAttribute("SUBJECT_VALUE");
            String statusQuestion = (String) session.getAttribute("STATUS_QUESTION");
            String pageIndex = request.getParameter("pageIndex");
            int index = 1;
            if (pageIndex != null) {
                index = Integer.parseInt(pageIndex);
            }
            int startPage = (index - 1) * PAGE_SIZE;
            List<Question> listSubPage = dao.subListQuestionSearch(txtSearch, subjectId, statusQuestion, startPage, PAGE_SIZE);
            if (listSubPage.isEmpty()) {
                session.setAttribute("LIST_SUB_PAGE", "noQuestion");
            } else {
                session.setAttribute("LIST_SUB_PAGE", listSubPage);
            }
          
            session.setAttribute("CURRENT_PAGE", index);
            url = ADMIN_SEARCH_PAGE;
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
