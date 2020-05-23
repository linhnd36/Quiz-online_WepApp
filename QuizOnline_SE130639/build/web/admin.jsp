<%-- 
    Document   : admin
    Created on : May 23, 2020, 12:33:32 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/style.css" />
        <title>Admin Page</title>
    </head>

    <body class="body">
        <form action="MainController">
            <header>
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <a class="navbar-brand" href="#">QuizOnline - Admin Page</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Create Question</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Search Question</a>
                            </li>
                            <li class="nav-item ml-5">
                                <span class="text-danger">Welcome ${sessionScope.NAME}</span>
                            </li>
                        </ul>
                        <div>
                            <button class="btn btn-outline-secondary" type="submit">
                                Log Out
                            </button>
                        </div>
                    </div>
                </nav>
            </header>
            <div class="bgcolor">
                <div class="container">
                    <h3 class="p-5">Input Question :</h3>
                    <form>
                        <div class="form-group">
                            <label>Question Content:</label>
                            <textarea class="form-control" rows="3"></textarea>
                        </div>
                        <label>Answer:</label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">A</span>
                            </div>
                            <input type="text" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">B</span>
                            </div>
                            <input type=" text" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">C</span>
                            </div>
                            <input type=" text" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">D</span>
                            </div>
                            <input type=" text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Subject:</label>
                            <select class="form-control">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                        <label>Select Correct answer:</label><br />
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="option1">
                            <label class="form-check-label">A</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="option2">
                            <label class="form-check-label">B</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="option2">
                            <label class="form-check-label">C</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="inlineRadioOptions" value="option2">
                            <label class="form-check-label">D</label>
                        </div><br />
                        <button type="button" class="btn btn-success mt-3" name="action" value="InpustQuestion">Input Question</button>
                    </form>
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