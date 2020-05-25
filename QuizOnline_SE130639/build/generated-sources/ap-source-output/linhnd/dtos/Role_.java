package linhnd.dtos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import linhnd.dtos.Account;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-25T22:44:37")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile CollectionAttribute<Role, Account> accountCollection;
    public static volatile SingularAttribute<Role, String> roleId;
    public static volatile SingularAttribute<Role, String> roleName;

}