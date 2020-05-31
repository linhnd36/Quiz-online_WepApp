<%-- 
    Document   : historyStudent
    Created on : May 31, 2020, 2:15:15 PM
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
        <title>Student Page</title>
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
                            <li class="nav-item active">
                                <a class="nav-link" href="HistoryController">History</a>
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
                    <div class="form-group pt-5 p-3">
                        <h5>Select subject :</h5>
                        <div class="row">
                            <select class="form-control col-6" name="subjectId">
                                <c:forEach var="subject" items="${LISTSUBJECT}">
                                    <option value="${subject.subjectId}"
                                            <c:if test="${SUBJECT_SEARCH.subjectId == subject.subjectId}" >
                                                selected
                                            </c:if>
                                            >
                                        ${subject.subjectId}-${subject.subjectName}
                                    </option>
                                </c:forEach>
                            </select>
                            <button class="btn btn-success" type="submit" value="searchStudent" name="action">Search</button> 
                        </div>
                    </div>


                    <c:if test="${LIST_SUB_HISTORY_PAGE != null && LIST_SUB_HISTORY_PAGE != 'noTest'}">

                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Score</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="test" items="${LIST_SUB_HISTORY_PAGE}" varStatus="counter">
                                    <tr>
                                        <th scope="row">${(CURRENT_HISTORY_PAGE - 1)*20 + counter.count}</th>
                                        <td>${test.testTitle}</td>
                                        <td>${test.score}</td>
                                        <td>${test.createDate}</td>
                                        <td>
                                            <c:url var="showDetailHistory" value="MainController">
                                                <c:param name="action" value="showDetailTest"/>
                                                <c:param name="testId" value="${test.testId}"/>
                                            </c:url>                      
                                            <a class="btn btn-info" href="${showDetailHistory}">Show Detail </a>                          
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </c:if>
                <c:if test="${LIST_SUB_HISTORY_PAGE == 'noTest'}">
                    <div class="alert alert-danger" role="alert">
                        No Test !
                    </div>
                </c:if>

                <nav>
                    <c:if test="${PAGE_SEARCH_HISTORY > 1}">
                        <ul class="pagination pt-2" style="margin-left: 40%;">
                            <c:forEach var="index" begin="1" end="${PAGE_SEARCH_HISTORY}">
                                <form action="LoadPageHistoryController">
                                    <li class="page-item 
                                        <c:if test="${index == CURRENT_HISTORY_PAGE}">
                                            active
                                        </c:if>
                                        ">
                                        <input class="page-link" type="submit" value="${index}" name="pageHistoryIndex"/>
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
