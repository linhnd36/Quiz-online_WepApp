<%-- 
    Document   : showDetailTest
    Created on : May 31, 2020, 8:09:20 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
        <link rel="stylesheet" href="style.css" />
        <title>Test Detail</title>
    </head>

    <body class="body">
        <form action="MainController">
            <header>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a class="navbar-brand" href="#">Quiz-Online</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item ">
                                <a class="nav-link" href="StudentController">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="HistoryController">History</a>
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
        </form>
        <div class="bgcolor">
            <div class="container">
                <form action="MainController">
                    <div class="p-5">
                        <h5 class="p-2">TiTle : ${TEST_DETAIL.testTitle}</h5>
                        <h5 class="p-2">Score : ${TEST_DETAIL.score}</h5>
                    </div>
                </form>
                <div>
                    <h4>Test Detail:</h4>
                    <c:forEach var="testQuestion" items="${LIST_TEST_QUESTION}" varStatus="counter">
                        <div class="col-sm-12 pt-2">
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-text">${(CURRENT_PAGE_DETAIL - 1)*20 + counter.count}. ${testQuestion.questionId.questionContent} 

                                        <c:if test="${testQuestion.questionId.correctAnswerID != testQuestion.answerId.answerId}" >
                                            <span style="color: red">( InCorrect )</span>
                                        </c:if>
                                        <c:if test="${testQuestion.questionId.correctAnswerID == testQuestion.answerId.answerId}" >
                                            <span style="color: green">( Correct )</span>
                                        </c:if>
                                    </p>
                                    <c:forEach var="answer" items="${testQuestion.questionId.answerCollection}" varStatus="counter">
                                        <div class="form-check ">

                                            <label
                                                <c:if test="${testQuestion.questionId.correctAnswerID == answer.answerId}" >
                                                    style="color: green"
                                                </c:if>
                                                <c:if test="${testQuestion.answerId.answerId == answer.answerId}" >
                                                    style="color: red"
                                                </c:if>
                                                >
                                                <c:choose  >
                                                    <c:when test="${counter.count == 1}">A.</c:when>
                                                    <c:when test="${counter.count == 2}">B.</c:when>
                                                    <c:when test="${counter.count == 3}">C.</c:when>
                                                    <c:when test="${counter.count == 4}">D.</c:when>
                                                </c:choose>
                                                ${answer.answerContent}</label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                    <nav>
                        <c:if test="${PAGE_DETAIL > 1}">
                            <ul class="pagination pt-2" style="margin-left: 40%;">
                                <c:forEach var="index" begin="1" end="${PAGE_DETAIL}">
                                    <form action="LoadPageDetailTestController">
                                        <li class="page-item 
                                            <c:if test="${index == CURRENT_PAGE_DETAIL}">
                                                active
                                            </c:if>
                                            ">
                                            <input class="page-link" type="submit" value="${index}" name="pageShowDetailIndex"/>
                                        </li>
                                    </form>
                                </c:forEach>
                            </ul>
                        </c:if>                      
                    </nav>
                </div>
            </div>
        </div>
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

