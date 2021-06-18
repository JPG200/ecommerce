package application.errors;

import application.commons.errors.ApplicationError;
import application.commons.errors.HttpStatusCode;

public class InputDataError extends ApplicationError{

    private final String message;

    public InputDataError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String errorCode() {
        return "INPUT_DATA_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }
}
