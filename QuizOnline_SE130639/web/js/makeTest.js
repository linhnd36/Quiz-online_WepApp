

this.timeRemaining = 60;
this.timeDisplay = document.getElementById("timeDisplay");
this.numberOfQuestion = 1;
this.currentNumberOfQuestion = 1;
this.currentNumberOfPage = 1;
this.currentDivQuestion = null;
this.currentDivPage = null;

var updateTime = function () {
    var hour = Math.floor(timeRemaining / 3600);
    var minute = Math.floor((timeRemaining - hour * 3600) / 60);
    var sec = timeRemaining % 60;
    timeDisplay.textContent = hour + ':' + minute + ':' + sec;
};

var removeTime = function () {
    localStorage.removeItem("Time");
}

var quizStart = function () {
    var x = localStorage.getItem("Time");
    if (x !== null) {
        timeRemaining = x;
    }
    setInterval(function () {
        timeRemaining--;
        localStorage.setItem("Time", timeRemaining);
        updateTime();
        if (timeRemaining < 0) {
            timeRemaining = 0;
            document.getElementById("btnSubmit").click();
            removeTime();
        }
    }, 1000);
};

var setDefaultQuiz = function (numberOfQuestion, timeOfExam) {
    this.timeRemaining = timeOfExam * 60;
    this.timeDisplay = document.getElementById("timeDisplay");
    this.numberOfQuestion = numberOfQuestion;
    this.currentNumberOfQuestion = 1;
    this.currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    this.currentDivPage = document.getElementById(currentNumberOfPage);
    currentDivQuestion.classList.remove("hidden");
    currentDivPage.classList.add("active");
};

var selectQuestion = function (n) {
    currentNumberOfQuestion = n % numberOfQuestion;
    currentNumberOfPage = n;
    if (currentNumberOfQuestion === 0) {
        currentNumberOfQuestion = numberOfQuestion;
    }
    if (this.currentDivQuestion !== null) {
        this.currentDivQuestion.classList.add("hidden");
        this.currentDivPage.classList.remove("active");
    }
    currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivPage = document.getElementById(currentNumberOfPage);
    currentDivQuestion.classList.remove("hidden");
    currentDivPage.classList.add("active");
};

var nextQuestion = function () {

    currentNumberOfQuestion = (currentNumberOfQuestion + 1) % numberOfQuestion;
    if (currentNumberOfQuestion === 0) {
        currentNumberOfQuestion = numberOfQuestion;
    }
    currentNumberOfPage = (currentNumberOfPage + 1) % numberOfQuestion;
    if (currentNumberOfPage === 0) {
        currentNumberOfPage = numberOfQuestion;
    }

    if (this.currentDivQuestion !== null) {
        this.currentDivQuestion.classList.add("hidden");
    }
    if (this.currentDivPage !== null) {
        this.currentDivPage.classList.remove("active");
    }
    currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivPage = document.getElementById(currentNumberOfPage);
    currentDivQuestion.classList.remove("hidden");
    currentDivPage.classList.add("active");

};

var previousQuestion = function () {
    currentNumberOfQuestion = (currentNumberOfQuestion - 1) % numberOfQuestion;
    if (currentNumberOfQuestion === 0) {
        currentNumberOfQuestion = numberOfQuestion;
    }

    currentNumberOfPage = (currentNumberOfPage - 1) % numberOfQuestion;
    if (currentNumberOfPage === 0) {
        currentNumberOfPage = numberOfQuestion;
    }
    if (this.currentDivQuestion !== null) {
        this.currentDivQuestion.classList.add("hidden");
    }
    if (this.currentDivPage !== null) {
        this.currentDivPage.classList.remove("active");
    }
    currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivPage = document.getElementById(currentNumberOfPage);
    currentDivQuestion.classList.remove("hidden");
    currentDivPage.classList.add("active");
};