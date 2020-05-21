package linhnd.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Question;
import linhnd.dtos.TestQuestions;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-21T13:24:53")
@StaticMetamodel(Answer.class)
public class Answer_ { 

    public static volatile SingularAttribute<Answer, Integer> answerId;
    public static volatile SingularAttribute<Answer, Question> questionId;
    public static volatile SingularAttribute<Answer, String> answerContent;
    public static volatile CollectionAttribute<Answer, TestQuestions> testQuestionsCollection;

}