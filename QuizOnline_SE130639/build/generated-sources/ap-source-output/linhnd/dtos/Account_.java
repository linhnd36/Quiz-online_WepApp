package linhnd.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Role;
import linhnd.dtos.Status;
import linhnd.dtos.Test;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-21T13:24:53")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, Status> statusId;
    public static volatile SingularAttribute<Account, Role> roleId;
    public static volatile CollectionAttribute<Account, Test> testCollection;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile SingularAttribute<Account, String> email;

}