<%-- 
    Document   : adminSearchPage
    Created on : May 26, 2020, 2:03:05 PM
    Author     : Duc Linh
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
        <title>Admin Search Page</title>
    </head>

    <body class="body">
        <form action="MainController">
            <header>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a class="navbar-brand" href="#">QuizOnline - Admin Page</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item ">
                                <a class="nav-link" href="adminInsertPage.jsp">Create Question</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="#">Search Question</a>
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
        <div class="bgcolor" style="height: 100%">
            <div class="container">
                <h1 class="p-5">Search Page</h1> 
                <form class="example" action="MainController">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" name="txtSearchQuestion" placeholder="Search Question" value="${SEARCH_VALUE}">
                        <div class="input-group-append">
                            <button class="btn btn-success" type="submit" value="Search" name="action">Search</button>
                        </div>                         
                    </div>
                    <div class="row col-md-5">
                        <div class="form-group col">
                            <label>Select subject :</label>
                            <select class="form-control" name="subjectId">
                                <c:forEach var="subject" items="${LiST_SUBJECT}">
                                    <option value="${subject.subjectId}" <c:if test="${SUBJECT_VALUE == subject.subjectId}" > selected </c:if> >
                                        ${subject.subjectId}-${subject.subjectName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col">
                            <label>Select status question :</label>
                            <select class="form-control" name="statusQuestion">
                                <option value="QuesActive" <c:if test="${STATUS_QUESTION == 'QuesActive'}" > selected </c:if> >Active</option>
                                <option value="QuesDelete" <c:if test="${STATUS_QUESTION == 'QuesDelete'}" > selected </c:if> >Delete</option>                                   
                                </select>
                            </div>
                        </div>

                    <c:if test="${LIST_SUB_PAGE != null && LIST_SUB_PAGE != 'noQuestion'}">
                        <c:forEach var="question" items="${LIST_SUB_PAGE}" varStatus="counter">
                            <form action="MainController">
                                <div class="col-sm-12 pt-2">
                                    <div class="card">
                                        <div class="card-body">
                                            <p class="card-text">
                                                ${(CURRENT_PAGE - 1)*20 + counter.count} .  ${question.questionContent}
                                            </p>
                                            <label>Answer:</label><br />
                                            <c:forEach var="answers" items="${question.answerCollection}" varStatus="counter">
                                                <div class="form-check ">
                                                    <label 
                                                        <c:if test="${question.correctAnswerID == answers.answerId}"> style="color: red"</c:if>
                                                        ><c:choose  >
                                                            <c:when test="${counter.count == 1}">A.</c:when>
                                                            <c:when test="${counter.count == 2}">B.</c:when>
                                                            <c:when test="${counter.count == 3}">C.</c:when>
                                                            <c:when test="${counter.count == 4}">D.</c:when>
                                                        </c:choose> ${answers.answerContent}</label>
                                                </div>
                                            </c:forEach>
                                            <input type="hidden" name="questionID" value="${question.questionId}"/>
                                            <button type="submit" class="btn btn-success mt-3" name="action" value="btnDetail">
                                                Update</button>
                                                <c:if test="${STATUS_QUESTION == 'QuesActive'}" > 
                                                <button type="submit" class="btn btn-danger mt-3" name="action" value="btnDelete">
                                                    Delete</button>
                                                </c:if>
                                                <c:if test="${STATUS_QUESTION != 'QuesActive'}" > 
                                                <button type="submit" class="btn btn-info mt-3" name="action" value="btnRestore">Restore</button>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </c:if>
                </form>
                <c:if test="${LIST_SUB_PAGE == 'noQuestion'}">
                    <div class="alert alert-danger" role="alert">
                        No question !
                    </div>
                </c:if>




                <nav>
                    <c:if test="${PAGE > 1}">
                        <ul class="pagination pt-2" style="margin-left: 40%;">
                            <c:forEach var="index" begin="1" end="${PAGE}">
                                <form action="LoadPageSearchController">
                                    <li class="page-item 
                                        <c:if test="${index == CURRENT_PAGE}">
                                            active
                                        </c:if>
                                        ">
                                        <input class="page-link" type="submit" value="${index}" name="pageIndex"/>
                                    </li>
                                </form>
                            </c:forEach>
                        </ul>
                    </c:if>
                </nav>
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