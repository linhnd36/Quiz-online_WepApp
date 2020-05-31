/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhnd.daos.TestDAO;
import linhnd.dtos.Test;
import linhnd.dtos.TestQuestions;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "GetDetailTestController", urlPatterns = {"/GetDetailTestController"})
public class GetDetailTestController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(GetDetailTestController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "LoadPageDetailTestController";
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
            String testIdString = request.getParameter("testId");
            int testId = Integer.parseInt(testIdString);
            TestDAO dao = new TestDAO();
            Test test = dao.getTestDetail(testId);
            List<TestQuestions> listTestQuestion = (List<TestQuestions>) test.getTestQuestionsCollection();
            int numberOfQuestion = listTestQuestion.size();
            int page = (int) Math.ceil((double) numberOfQuestion / PAGE_SIZE);
            session.setAttribute("TEST_DETAIL", test);
            session.setAttribute("PAGE_DETAIL", page);

            url = SUCCESS;
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
