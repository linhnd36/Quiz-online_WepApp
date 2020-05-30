<%-- 
    Document   : studentTest
    Created on : May 29, 2020, 11:10:07 PM
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
        <title>Student Test</title>
        <style>
            .hidden {
                display: none;
                visibility: hidden;
            }
        </style>
    </head>

    <body class="body">
        <form action="MainController">
            <header>       
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a class="navbar-brand" href="#">Quiz-Online</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">History</a>
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
                <form action="MainController" method="POST">
                    <div class="row p-5">
                        <h3>${SUBJECT_TEST.subjectId} - ${SUBJECT_TEST.subjectName}</h3>
                        <button type="button" class="btn btn-success ml-5" name="btnSubmit" value="testFinish">Submit</button>
                    </div>
                    <h4>Time:</h4>
                    <h4 id="timeDisplay">00:00:00</h4>
                    <div class="col-sm-12 pt-2">
                        <c:forEach var="mapQuestion" items="${MAP_QUESTION_TEST}">
                            <c:set var="question" value="${mapQuestion.value}"/>
                            <div class="card m-2 hidden " id="${mapQuestion.key}">
                                <h5 class="m-2">${mapQuestion.key}:</h5>
                                <p class="card-text m-2">${question.questionContent} </p>
                                <label class="pl-2">Select your answer:</label>
                                <c:forEach var="answer" items="${question.answerCollection}" varStatus="counter">
                                    <div class="form-check pl-5">
                                        <input class="form-check-input" type="radio" name="${mapQuestion.key}" value="${answer}">
                                        <label class="">
                                            <c:choose  >
                                                <c:when test="${counter.count == 1}">A. </c:when>
                                                <c:when test="${counter.count == 2}">B. </c:when>
                                                <c:when test="${counter.count == 3}">C. </c:when>
                                                <c:when test="${counter.count == 4}">D. </c:when>
                                            </c:choose>${answer.answerContent} </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:forEach> 
                </form>   
                <div style="margin-top:20px; width: 1000px;">
                    <ul class="row" style="list-style: none;">
                        <li class="page-item active pr-3"><a class="page-link" href="#" onclick="previousQuestion()">Previous</a></li>
                            <c:forEach var="count" begin="1" end="${SUBJECT_TEST.numberOfQuestions}" step="1">
                            <li class="page-item " id="${count}"><a class="page-link" href="#" onclick="selectQuestion(${count})">${count}</a></li>
                            </c:forEach>
                        <li class="page-item active pl-3"><a class="page-link" href="#" onclick="nextQuestion()">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/makeTest.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
crossorigin="anonymous"></script>
<script>
                                setDefaultQuiz(${SUBJECT_TEST.numberOfQuestions}, ${SUBJECT_TEST.timeTest});
                                quizStart();
</script>
</body>

</html>