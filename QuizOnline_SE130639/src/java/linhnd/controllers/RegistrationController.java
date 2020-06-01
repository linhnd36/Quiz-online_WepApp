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
import linhnd.daos.AccountDAO;
import linhnd.dtos.AccountCrearteError;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Duc Linh
 */
@WebServlet(name = "RegistrationController", urlPatterns = {"/RegistrationController"})
public class RegistrationController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(RegistrationController.class);

    private static final String LOGINPAGE = "login.jsp";
    private static final String SIGNUPPAGE = "signUp.jsp";
    private static final String ERRORPAGE = "error.jsp";

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
        String url = SIGNUPPAGE;
        boolean foundErr = false;
        try {
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String passwordConfirm = request.getParameter("txtConfirmPassword");
            AccountCrearteError error = new AccountCrearteError();
            AccountDAO dao = new AccountDAO();

            if (dao.checkEmailAvailability(email.trim())) {
                foundErr = true;
                error.setEmailIsExited("Email is Exited");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                error.setPasswordLeghtError("Password string rerequired 6 to 20");
            }
            if (!password.trim().equals(passwordConfirm.trim())) {
                foundErr = true;
                error.setConfirmNoMatched("Confirm not matched");
            }
            if (foundErr) {
                request.setAttribute("CREATEERROR", error);
            } else {
                String passwordSha256 = DigestUtils.sha256Hex(password);
                if (dao.createNewAccount(email, name, passwordSha256)) {
                    request.setAttribute("CREATESUCESS", "Create account sucess, pls Login");
                    url = LOGINPAGE;
                } else {
                    request.setAttribute("ERROR", "Create account error");
                    url = ERRORPAGE;
                }
            }
        } catch (Exception e) {
            LOGGER.fatal(e.getMessage());
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
