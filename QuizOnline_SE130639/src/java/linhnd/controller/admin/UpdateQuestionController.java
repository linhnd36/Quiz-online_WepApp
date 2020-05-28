/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import linhnd.daos.QuestionDAO;
import linhnd.dtos.Answer;
import linhnd.dtos.Question;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateQuestionController", urlPatterns = {"/UpdateQuestionController"})
public class UpdateQuestionController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(UpdateQuestionController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "error.jsp";

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
        String questionId = request.getParameter("questionID");
        String questionContent = request.getParameter("txtQuestionContent");
        String questionAnswarA = request.getParameter("txtQuestionAnswerA");
        String questionAnswarB = request.getParameter("txtQuestionAnswerB");
        String questionAnswarC = request.getParameter("txtQuestionAnswerC");
        String questionAnswarD = request.getParameter("txtQuestionAnswerD");
        String correctAnswerAdChoose = request.getParameter("txtCorrectAnswer");
        String subjectId = request.getParameter("txtSelectSubject");
        String correctAnswer = null;

        int questionIdInt = Integer.parseInt(questionId);
        int correctAnswerId = -1;

        try {
            QuestionDAO dao = new QuestionDAO();
            Question question = dao.getQuestionById(questionIdInt);
            List<Answer> listAnswer = (List<Answer>) question.getAnswerCollection();
            listAnswer.get(0).setAnswerContent(questionAnswarA);
            listAnswer.get(1).setAnswerContent(questionAnswarB);
            listAnswer.get(2).setAnswerContent(questionAnswarC);
            listAnswer.get(3).setAnswerContent(questionAnswarD);
            if (questionContent.trim().equals("")) {
                request.setAttribute("ERRORUPDATE", "Question Content can't blank !");
            } else {
                if (correctAnswerAdChoose == null) {
                    request.setAttribute("ERRORINPUT", "Pls choose Correct answer !");
                } else {
                    switch (correctAnswerAdChoose) {
                        case "optionA":
                            correctAnswer = questionAnswarA;
                            break;
                        case "optionB":
                            correctAnswer = questionAnswarB;
                            break;
                        case "optionC":
                            correctAnswer = questionAnswarC;
                            break;
                        case "optionD":
                            correctAnswer = questionAnswarD;
                            break;
                        default:
                            break;
                    }
                }
                for (Answer answer : listAnswer) {
                    if (answer.getAnswerContent().equals(correctAnswer)) {
                        correctAnswerId = answer.getAnswerId();
                    }
                }

                if (dao.updateQuestion(questionIdInt, questionContent, subjectId, listAnswer, correctAnswerId)) {
                    url = SUCCESS;
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
