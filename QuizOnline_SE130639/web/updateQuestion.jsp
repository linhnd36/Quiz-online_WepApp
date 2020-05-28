<%-- 
    Document   : updateQuestion
    Created on : May 28, 2020, 10:26:00 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/style.css" />
        <title>Admin Insert Page</title>
    </head>

    <body class="body">
        <form action="MainController">
            <header>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a class="navbar-brand" href="#">QuizOnline - Admin Page</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item ">
                                <a class="nav-link" href="AdminController">Create Question</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="PageSearchController">Search Question</a>
                            </li>
                            <li class="nav-item ml-5">
                                <span class="text-danger">Welcome ${sessionScope.NAME}</span>
                            </li>
                        </ul>
                        <div>
                            <button class="btn btn-outline-secondary" type="submit" value="Logout" name="action">
                                Log Out
                            </button>
                        </div>
                    </div>
                </nav>
            </header>
            <div class="bgcolor">
                <div class="container">
                    <c:set var="question" value="${requestScope.DETAIL_QUESTION}"/>
                    <input type="hidden" name="questionID" value="${question.questionId}"/>
                    <h3 class="p-5">Update Question :</h3>
                    <div class="form-group">
                        <label>Question Content:</label>
                        <textarea class="form-control" rows="3" name="txtQuestionContent">${question.questionContent}</textarea>
                    </div>
                    <label>Answer:</label>
                    <c:forEach var="answers" items="${question.answerCollection}" varStatus="counter">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <c:choose  >
                                        <c:when test="${counter.count == 1}">A</c:when>
                                        <c:when test="${counter.count == 2}">B</c:when>
                                        <c:when test="${counter.count == 3}">C</c:when>
                                        <c:when test="${counter.count == 4}">D</c:when>
                                    </c:choose>
                                </span>
                            </div>
                            <input type="text" class="form-control" 
                                   <c:choose  >
                                       <c:when test="${counter.count == 1}">name="txtQuestionAnswerA"</c:when>
                                       <c:when test="${counter.count == 2}">name="txtQuestionAnswerB"</c:when>
                                       <c:when test="${counter.count == 3}">name="txtQuestionAnswerC"</c:when>
                                       <c:when test="${counter.count == 4}">name="txtQuestionAnswerD"</c:when>
                                   </c:choose>
                                   value="${answers.answerContent}">
                        </div>
                    </c:forEach>
                    <div class="form-group">
                        <label>Subject:</label>
                        <select class="form-control" name="txtSelectSubject">
                            <c:set var="ListSubject" value="${sessionScope.SUBJECT}"/>
                            <c:forEach var="subjectDao" items="${ListSubject}">
                                <option value="${subjectDao.subjectId}">${subjectDao.subjectId}-${subjectDao.subjectName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label>Correct answer:</label><br />
                    <c:forEach var="answers" items="${question.answerCollection}" varStatus="counter">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="txtCorrectAnswer"
                                   <c:if test="${answers.answerId == question.correctAnswerID}">checked</c:if>
                                   <c:choose  >
                                       <c:when test="${counter.count == 1}">value="optionA"</c:when>
                                       <c:when test="${counter.count == 2}">value="optionB"</c:when>
                                       <c:when test="${counter.count == 3}">value="optionC"</c:when>
                                       <c:when test="${counter.count == 4}">value="optionD"</c:when>
                                   </c:choose>
                                   >
                            <label class="form-check-label">
                                <c:choose  >
                                    <c:when test="${counter.count == 1}">A</c:when>
                                    <c:when test="${counter.count == 2}">B</c:when>
                                    <c:when test="${counter.count == 3}">C</c:when>
                                    <c:when test="${counter.count == 4}">D</c:when>
                                </c:choose>
                            </label>
                        </div>

                    </c:forEach><br />
                    <button type="submit" class="btn btn-success mt-3" name="action" value="updateQuestion">Update Question</button>                
                </div>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
    </body>

</html>