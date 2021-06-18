package application.errors;

import application.commons.errors.ApplicationError;
import application.commons.errors.HttpStatusCode;
import application.domain.*;

import java.util.Map;

public class ProductAlredyExist extends ApplicationError{
    private final ProductId productId;


    public ProductAlredyExist(ProductId product) {
        this.productId = product;
    }

    public ProductId getProductId() {
        return productId;
    }

    @Override
    public String getMessage() {
        return "The product with id number: " + productId.getValue() + " already exists.";
    }

    @Override
    public String errorCode() {
        return "PRODUCT_ALREADY_EXISTS_ERROR";
    }

    @Override
    public HttpStatusCode httpStatusCode() {
        return HttpStatusCode.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> metadata() {
        return Map.of(
                "productId: ", productId
        );
    }

}
