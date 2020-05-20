CREATE DATABASE QuizOnlineLinhNd
CREATE TABLE Status
(
  StatusName VARCHAR(30) NOT NULL,
  StatusId VARCHAR(30) NOT NULL,
  PRIMARY KEY (StatusId)
);

CREATE TABLE Role
(
  RoleId VARCHAR(30) NOT NULL,
  RoleName VARCHAR(30) NOT NULL,
  PRIMARY KEY (RoleId)
);

CREATE TABLE Subject
(
  SubjectId VARCHAR(30) NOT NULL,
  NumberOfQuestions INT NOT NULL,
  SubjectName VARCHAR(30) NOT NULL,
  TimeTest INT NOT NULL,
  PRIMARY KEY (SubjectId)
);

CREATE TABLE Answer
(
  AnswerId VARCHAR(30) NOT NULL,
  AnswerContent VARCHAR(30) NOT NULL,
  PRIMARY KEY (AnswerId)
);

CREATE TABLE Account
(
  Email VARCHAR(255) NOT NULL,
  Name VARCHAR(30) NOT NULL,
  Password VARCHAR(30) NOT NULL,
  StatusId VARCHAR(30) NOT NULL,
  RoleId VARCHAR(30) NOT NULL,
  PRIMARY KEY (Email),
  FOREIGN KEY (StatusId) REFERENCES Status(StatusId),
  FOREIGN KEY (RoleId) REFERENCES Role(RoleId)
);

CREATE TABLE Question
(
  QuestionId VARCHAR(30) NOT NULL,
  CreateDate DATE NOT NULL,
  QuestionContent VARCHAR(255) NOT NULL,
  CorrectAnswerID VARCHAR(30) NOT NULL,
  SubjectId VARCHAR(30) NOT NULL,
  AnswerId VARCHAR(30) NOT NULL,
  StatusId VARCHAR(30) NOT NULL,
  PRIMARY KEY (QuestionId),
  FOREIGN KEY (SubjectId) REFERENCES Subject(SubjectId),
  FOREIGN KEY (AnswerId) REFERENCES Answer(AnswerId),
  FOREIGN KEY (StatusId) REFERENCES Status(StatusId)
);

CREATE TABLE Test
(
  TestId VARCHAR(30) NOT NULL,
  TestTitle VARCHAR(30) NOT NULL,
  Score FLOAT NULL,
  CreateDate DATE NOT NULL,
  Email VARCHAR(255) NOT NULL,
  PRIMARY KEY (TestId),
  FOREIGN KEY (Email) REFERENCES Account(Email)
);

CREATE TABLE TestQuestions
(
  TestQuestionsId INT NOT NULL,
  TestId VARCHAR(30) NOT NULL,
  QuestionId VARCHAR(30) NOT NULL,
  AnswerId VARCHAR(30) NOT NULL,
  PRIMARY KEY (TestQuestionsId),
  FOREIGN KEY (TestId) REFERENCES Test(TestId),
  FOREIGN KEY (QuestionId) REFERENCES Question(QuestionId),
  FOREIGN KEY (AnswerId) REFERENCES Answer(AnswerId)
);
