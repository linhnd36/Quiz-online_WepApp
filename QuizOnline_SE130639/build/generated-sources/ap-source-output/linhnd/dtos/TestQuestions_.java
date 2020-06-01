package linhnd.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Answer;
import linhnd.dtos.Question;
import linhnd.dtos.Test;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-01T17:50:44")
@StaticMetamodel(TestQuestions.class)
public class TestQuestions_ { 

    public static volatile SingularAttribute<TestQuestions, Answer> answerId;
    public static volatile SingularAttribute<TestQuestions, Question> questionId;
    public static volatile SingularAttribute<TestQuestions, Test> testId;
    public static volatile SingularAttribute<TestQuestions, Integer> testQuestionsId;

}