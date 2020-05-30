this.timeRemaining = 60;
this.timeDisplay = document.getElementById("timeDisplay");
this.numberOfQuestion = 1;
this.currentNumberOfQuestion = 1;
this.currentDivQuestion = null;

var updateTime = function () {
    var hour = Math.floor(timeRemaining / 3600);
    var minute = Math.floor((timeRemaining - hour * 3600) / 60);
    var sec = timeRemaining % 60;
    timeDisplay.textContent = hour + ':' + minute + ':' + sec;
};

var quizStart = function () {
    setInterval(function () {
        timeRemaining--;
        updateTime();
        if (timeRemaining < 0) {
            timeRemaining = 0;
            document.getElementById("btnSubmit").click();
        }
    }, 1000);
};

var setDefaultQuiz = function (numberOfQuestion, timeOfExam) {
    this.timeRemaining = timeOfExam * 60;
    this.timeDisplay = document.getElementById("timeDisplay");
    this.numberOfQuestion = numberOfQuestion;
    this.currentNumberOfQuestion = 1;
    this.currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivQuestion.classList.remove("hidden");
};

var selectQuestion = function (n) {
    currentNumberOfQuestion = n % numberOfQuestion;
    if (currentNumberOfQuestion === 0) {
        currentNumberOfQuestion = numberOfQuestion;
    }
    if (this.currentDivQuestion !== null) {
        this.currentDivQuestion.classList.add("hidden");
    }
    currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivQuestion.classList.remove("hidden");
};

var nextQuestion = function () {
    currentNumberOfQuestion = (currentNumberOfQuestion + 1) % numberOfQuestion;
    if (currentNumberOfQuestion === 0) {
        currentNumberOfQuestion = numberOfQuestion;
    }
    if (this.currentDivQuestion !== null) {
        this.currentDivQuestion.classList.add("hidden");
    }
    currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivQuestion.classList.remove("hidden");
};

var previousQuestion = function () {
    currentNumberOfQuestion = (currentNumberOfQuestion - 1) % numberOfQuestion;
    if (currentNumberOfQuestion === 0) {
        currentNumberOfQuestion = numberOfQuestion;
    }
    if (this.currentDivQuestion !== null) {
        this.currentDivQuestion.classList.add("hidden");
    }
    currentDivQuestion = document.getElementById("Question " + currentNumberOfQuestion);
    currentDivQuestion.classList.remove("hidden");
};