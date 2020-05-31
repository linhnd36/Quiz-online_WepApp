/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import linhnd.daos.AnswerDAO;
import linhnd.daos.TestDAO;
import linhnd.dtos.Question;
import linhnd.dtos.Subject;
import linhnd.dtos.TestQuestions;
import org.apache.log4j.Logger;

/**
 *
 * @author PC
 */
@WebServlet(name = "FinishTestController", urlPatterns = {"/FinishTestController"})
public class FinishTestController extends HttpServlet {

    static Logger LOGGER = Logger.getLogger(FinishTestController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCESS = "testDetail.jsp";

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
            String email = (String) session.getAttribute("EMAIL");
            LinkedHashMap<String, Question> listQuestionTest = (LinkedHashMap<String, Question>) session.getAttribute("MAP_QUESTION_TEST");
            Subject subject = (Subject) session.getAttribute("SUBJECT_TEST");
            List<TestQuestions> listAnswerStudentChoose = new ArrayList<>();
            float score = 0;
            int numberCorrectAnswer = 0;
            AnswerDAO daoAnswer = new AnswerDAO();
            if (listQuestionTest != null) {
                for (String key : listQuestionTest.keySet()) {
                    String answerStudentChoose = request.getParameter(key);

                    TestQuestions testQuestion = new TestQuestions();
                    
                    if (answerStudentChoose != null) {
                        testQuestion.setAnswerId(daoAnswer.getAnswer(Integer.parseInt(answerStudentChoose)));
                        testQuestion.setQuestionId(listQuestionTest.get(key));
                        listAnswerStudentChoose.add(testQuestion);
                        if (answerStudentChoose.equals(listQuestionTest.get(key).getCorrectAnswerID())) {
                            numberCorrectAnswer++;
                        }
                    } else {
                        testQuestion.setAnswerId(daoAnswer.getAnswer(2));
                        testQuestion.setQuestionId(listQuestionTest.get(key));
                        listAnswerStudentChoose.add(testQuestion);
                    }
                }
            }
            score = ((float) numberCorrectAnswer / subject.getNumberOfQuestions()) * 10;
            // insert test 
            TestDAO daoTest = new TestDAO();
            if (daoTest.newTest(subject.getSubjectId() + "-" + subject.getSubjectName(), score, email, listAnswerStudentChoose)) {
                url = SUCESS;
                request.setAttribute("SCORE", score);
                request.setAttribute("NUMBER_OF_CORRECT", numberCorrectAnswer);
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
