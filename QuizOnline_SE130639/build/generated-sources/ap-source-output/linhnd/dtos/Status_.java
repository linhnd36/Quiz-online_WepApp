package linhnd.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Account;
import linhnd.dtos.Question;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-25T22:44:37")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile CollectionAttribute<Status, Question> questionCollection;
    public static volatile CollectionAttribute<Status, Account> accountCollection;
    public static volatile SingularAttribute<Status, String> statusId;
    public static volatile SingularAttribute<Status, String> statusName;

}