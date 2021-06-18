package application.model.Create;

import application.commons.operation.ApplicationResponse;
import application.domain.Product;

public class CreateProductResponse implements ApplicationResponse {
    private final Product product;

    public CreateProductResponse(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "CreateProductResponse{" +
                "Product= " + product +
                '}';
    }

}
