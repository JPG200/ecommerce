package application.errors;

import application.commons.NonEmptyString;
import application.commons.errors.ApplicationError;
import application.commons.errors.HttpStatusCode;
import application.domain.*;

import java.util.Map;

public class UserAlredyExist extends ApplicationError {

    private final NonEmptyString User;

    public UserAlredyExist(NonEmptyString user) {
        this.User = user;
    }

    public NonEmptyString getUser() {
        return User;
    }

    @Override
    public String getMessage() {
        return "The User with this name: " + User.getValue() + " already exists.";
    }

    @Override
    public String errorCode() {
        return "User_ALREADY_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "User: ", User.getValue()
        );
    }
}
