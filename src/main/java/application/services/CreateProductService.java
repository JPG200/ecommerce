package application.services;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;
import application.domain.*;
import application.errors.InputDataError;
import application.errors.ProductAlredyExist;
import application.model.Create.CreateProductRequest;
import application.model.Create.CreateProductResponse;
import application.ports.in.CreateProductUseCase;
import application.ports.out.ProductRepository;

import java.util.Optional;

public class CreateProductService implements CreateProductUseCase {
    private final ProductRepository repository;

    public CreateProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public CreateProductResponse execute(CreateProductRequest request){
        Product product = validateInput(request);
        NonEmptyString ProductName = product.getProductName();
        ProductId idNumber = product.getProductId();
        Optional<Product> productByName = repository.getProductByName(ProductName);
        if (productByName.isPresent()) {
            throw new ProductAlredyExist(idNumber);
        }

        repository.storeProduct(product);

        return new CreateProductResponse(product);
    }

    private Product validateInput(CreateProductRequest request) {
        try {
            NonEmptyCI ProductCI = new NonEmptyCI(request.getProductCI());
            NonEmptyString ProductName = new NonEmptyString(request.getProductName());
            NonEmptyString ProductDescription = new NonEmptyString(request.getProductDescription());
            NonEmptyTax ProductTax = new NonEmptyTax(request.getProductTax());
            NonEmptyPrice ProductPrice = new NonEmptyPrice(request.getProductPrice());
            ProductState ProductStates = ProductState.valueOf(request.getProductState());
            ProductId ProductId = new ProductId(request.getProductId());

            return new Product(
                    ProductId,
                    ProductName,
                    ProductPrice,
                    ProductTax,
                    ProductDescription,
                    ProductStates,
                    ProductCI
            );
        } catch (RuntimeException e) {
            throw new InputDataError(e.getMessage());
        }
    }
}
