package application.domain;

import application.commons.*;

public class User {
    private final NonEmptyString userName;
    private final NonEmptyString userPassword;
    private final TypeUser userType;

    public User(NonEmptyString userName, NonEmptyString userPassword, TypeUser userType) {
        Validate.notNull(userName,"userName can not be null");
        Validate.notNull(userPassword,"userPassword can not be null");
        Validate.notNull(userType,"userType can not be null");

        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }


    public TypeUser getUserType() {return userType;}

    public NonEmptyString getUserPassword() {return userPassword;}

    public NonEmptyString getUserName() {return userName;}
}
