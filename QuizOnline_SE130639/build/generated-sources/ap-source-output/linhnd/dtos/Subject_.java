package linhnd.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Question;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-30T22:44:34")
@StaticMetamodel(Subject.class)
public class Subject_ { 

    public static volatile CollectionAttribute<Subject, Question> questionCollection;
    public static volatile SingularAttribute<Subject, Integer> timeTest;
    public static volatile SingularAttribute<Subject, Integer> numberOfQuestions;
    public static volatile SingularAttribute<Subject, String> subjectId;
    public static volatile SingularAttribute<Subject, String> subjectName;

}