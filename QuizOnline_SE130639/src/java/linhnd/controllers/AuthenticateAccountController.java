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
import javax.servlet.http.HttpSession;
import linhnd.daos.AccountDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "AuthenticateAccountController", urlPatterns = {"/AuthenticateAccountController"})
public class AuthenticateAccountController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(AuthenticateAccountController.class);
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "StudentController";
    private static final String ERROR_CODE = "authenticaAccount.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occursF
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("EMAIL");
            String codeRandom = (String) session.getAttribute("RANDOM_CODE");
            String txtInputCode = request.getParameter("txtCode");
            if (txtInputCode != null) {
                if (codeRandom.equals(txtInputCode)) {
                    AccountDAO dao = new AccountDAO();
                    if (dao.updateStatus(email)) {
                        url = SUCCESS;
                    }
                } else {
                    url = ERROR_CODE;
                    request.setAttribute("ERROR_CODE", "Incorrect authentication code");
                }
            } else {
                url = ERROR_CODE;
                request.setAttribute("ERROR_CODE", "Input code");
            }

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
