package application.model.Create;

import application.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateUserRequest implements ApplicationRequest {
    private String userName;
    private String userPass;
    private String userType;


    public CreateUserRequest(String userName, String userPass, String userType) {
        this.userName = userName;
        this.userPass = userPass;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return Objects.equals(userName, that.userName) && Objects.equals(userPass, that.userPass) && Objects.equals(userType, that.userType);
    }

    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userType='" + userType +
                '}';
    }
}
