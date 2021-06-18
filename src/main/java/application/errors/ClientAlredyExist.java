package application.errors;


import application.commons.errors.ApplicationError;
import application.commons.errors.HttpStatusCode;

import application.domain.IdentificationNumber;

import java.util.Map;

public class ClientAlredyExist extends ApplicationError {
    private final IdentificationNumber ClientObj;

    public ClientAlredyExist(IdentificationNumber ClientObj) {
        this.ClientObj=ClientObj;
    }

    public IdentificationNumber getClientObj() {
        return ClientObj;
    }

    @Override
    public String getMessage() {
        return "The Client with this id Number: " + ClientObj.getValue() + " already exists.";
    }

    @Override
    public String errorCode() {
        return "CLIENT_ALREADY_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "CLIENT: ", ClientObj.getValue()
        );
    }
}
