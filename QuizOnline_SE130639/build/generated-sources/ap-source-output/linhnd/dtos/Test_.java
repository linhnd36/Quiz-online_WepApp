package linhnd.dtos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Account;
import linhnd.dtos.TestQuestions;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-25T22:44:37")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile SingularAttribute<Test, Double> score;
    public static volatile SingularAttribute<Test, String> testId;
    public static volatile CollectionAttribute<Test, TestQuestions> testQuestionsCollection;
    public static volatile SingularAttribute<Test, String> testTitle;
    public static volatile SingularAttribute<Test, Account> email;
    public static volatile SingularAttribute<Test, Date> createDate;

}