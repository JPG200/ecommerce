package application.services;

import application.commons.*;
import application.domain.*;
import application.errors.ClientAlredyExist;
import application.errors.ProductAlredyExist;
import application.model.Create.CreateClientRequest;
import application.model.Create.CreateClientResponse;
import application.model.Create.CreateProductRequest;
import application.model.Create.CreateProductResponse;
import application.ports.out.ClientRepository;
import application.ports.out.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductServiceTest {

    @Test
    void ifProductDoesNotExistsItGetsCreated() {
        // arrange
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        Mockito.when(repository.getProductByName(ArgumentMatchers.any(NonEmptyString.class)))
                .thenReturn(Optional.empty());
        CreateProductService createProductService = new CreateProductService(repository);
        final int idNumber = 12345678;
        CreateProductRequest request = new CreateProductRequest(
                12345678,
                "PRODUCT_NAME",
                100000.000,
                0.19,
                "PRODUCT_DESCRIPTION",
                "PRODUCT_STATE",
                1
        );

        // act
        CreateProductResponse response = createProductService.execute(request);

        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createProductService.execute(request)),
                () -> assertEquals(
                        response.getProduct().getProductId().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getProductByName(ArgumentMatchers.any(NonEmptyString.class))
        );
    }


    @Test
    void ifProductExistsItThrowsAnException() {
        Product Product = new Product(
                new ProductId(12345678),
                new NonEmptyString("PRODUCT_NAME"),
                new NonEmptyPrice(100000.000),
                new NonEmptyTax(0.19),
                new NonEmptyString("PRODUCT_DESCRIPTION"),
                ProductState.OutofStock,
                new NonEmptyCI(0)
        );

        ProductRepository repository = Mockito.mock(ProductRepository.class);
        Mockito.when(repository.getProductByName(ArgumentMatchers.any(NonEmptyString.class)));

        CreateProductService createProductService = new CreateProductService(repository);
        CreateProductRequest request = new CreateProductRequest(
                Product.getProductId().getValue(),
                Product.getProductName().getValue(),
                Product.getProductPrice().getValue(),
                Product.getProductTax().getValue(),
                Product.getProductDescription().getValue(),
                Product.getProductState().name(),
                Product.getProductCI().getValue()
        );

        assertAll(
                () -> assertThrows(ProductAlredyExist.class, () -> createProductService.execute(request)),
                () -> Mockito.verify(repository, Mockito.times(0))
                        .storeProduct(ArgumentMatchers.any(Product.class))
        );

    }
}
