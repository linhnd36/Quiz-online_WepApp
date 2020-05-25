/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import linhnd.daos.AnswerDAO;
import linhnd.daos.QuestionDAO;
import linhnd.dtos.Answer;
import linhnd.dtos.Question;

/**
 *
 * @author PC
 */
@WebServlet(name = "InsertQuestionController", urlPatterns = {"/InsertQuestionController"})
public class InsertQuestionController extends HttpServlet {

    private static final String ERRORPAGE = "error.jsp";
    private static final String ADMINPAGE = "admin.jsp";

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
        String questionContent = request.getParameter("txtQuestionContent");
        String questionAnswarA = request.getParameter("txtQuestionAnswerA");
        String questionAnswarB = request.getParameter("txtQuestionAnswerB");
        String questionAnswarC = request.getParameter("txtQuestionAnswerC");
        String questionAnswarD = request.getParameter("txtQuestionAnswerD");
        String questionCorrect = request.getParameter("txtCorrectAnswer");
        String subjectId = request.getParameter("txtSelectSubject");
        String correctAnswer = null;
        String url = ERRORPAGE;

        try {
            List<String> listAnswer = new ArrayList<>();
            listAnswer.add(questionAnswarA.trim());
            listAnswer.add(questionAnswarB.trim());
            listAnswer.add(questionAnswarC.trim());
            listAnswer.add(questionAnswarD.trim());

            if (questionCorrect.equals("optionA")) {
                correctAnswer = questionAnswarA;
            } else if (questionCorrect.equals("optionB")) {
                correctAnswer = questionAnswarB;
            } else if (questionCorrect.equals("optionC")) {
                correctAnswer = questionAnswarC;
            } else if (questionCorrect.equals("optionD")) {
                correctAnswer = questionAnswarD;
            }

            QuestionDAO dao = new QuestionDAO();
            //insert new question
            if (dao.newQuestion(questionContent.trim(), subjectId)) {
                Question question = dao.getQuestionId();
                AnswerDAO daoAnswer = new AnswerDAO();
                // insert list answer of the question
                if (daoAnswer.newAnswer(listAnswer, question.getQuestionId())) {
                    int correctAnswerId = daoAnswer.getAnswerId(correctAnswer, question.getQuestionId());
                    if (correctAnswerId > 0) {
                        //update correct answer of the question
                        if (dao.updateCorrecAnswerForQuestion(question.getQuestionId(), String.valueOf(correctAnswerId))) {
                            url = ADMINPAGE;
                            request.setAttribute("INPUTSUCCESS", "Input Successfull !");
                        } else {
                            request.setAttribute("ERROR", "Inpust question Error !");
                        }
                    } else {
                        request.setAttribute("ERROR", "Inpust question Error !");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Inpust question Error !");
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
