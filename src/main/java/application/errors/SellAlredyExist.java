package application.errors;


import application.commons.NonEmptyString;
import application.commons.errors.ApplicationError;
import application.commons.errors.HttpStatusCode;



import java.util.Map;

public class SellAlredyExist extends ApplicationError {
    private final NonEmptyString SellObj;

    public SellAlredyExist(NonEmptyString SellObj) {
        this.SellObj = SellObj;
    }

    public NonEmptyString getSellObj() {
        return SellObj;
    }

    @Override
    public String getMessage() {
        return "The Sell with this id: " + SellObj.getValue() + " already exists.";
    }

    @Override
    public String errorCode() {
        return "SELL_ALREADY_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "SELL: ", SellObj.getValue()
        );
    }
}
