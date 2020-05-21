package linhnd.dtos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Answer;
import linhnd.dtos.Status;
import linhnd.dtos.Subject;
import linhnd.dtos.TestQuestions;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-21T13:24:53")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, String> questionContent;
    public static volatile SingularAttribute<Question, Integer> questionId;
    public static volatile SingularAttribute<Question, String> correctAnswerID;
    public static volatile SingularAttribute<Question, Status> statusId;
    public static volatile CollectionAttribute<Question, Answer> answerCollection;
    public static volatile CollectionAttribute<Question, TestQuestions> testQuestionsCollection;
    public static volatile SingularAttribute<Question, Subject> subjectId;
    public static volatile SingularAttribute<Question, Date> createDate;

}