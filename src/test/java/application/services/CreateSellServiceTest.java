package application.services;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;
import application.domain.*;
import application.errors.ProductAlredyExist;
import application.model.Create.CreateProductRequest;
import application.model.Create.CreateProductResponse;
import application.model.Create.CreateSellRequest;
import application.model.Create.CreateSellResponse;
import application.ports.out.ProductRepository;
import application.ports.out.SellRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateSellServiceTest {

    @Test
    void ifSellDoesNotExistsItGetsCreated() {
        // arrange
        SellRepository repository = Mockito.mock(SellRepository.class);
        Mockito.when(repository.getSellById(ArgumentMatchers.any(NonEmptyString.class)))
                .thenReturn(Optional.empty());
        CreateSellService createSellService = new CreateSellService(repository);
        final int idNumber = 12345678;
        final String idNumber2="12345678";
        CreateSellRequest request = new CreateSellRequest(
                idNumber,
                idNumber2,
                "PRODUCT_NAME",
                100000.000,
                0.19,
                1,
                "CLIENT_NAME",
                "CLIENT_LASTNAME",
                "CLIENT_UBICATION",
                idNumber2,
                "CC",
                "Registrada"
        );

        // act
        CreateSellResponse response = createSellService.execute(request);

        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createSellService.execute(request)),
                () -> assertEquals(
                        response.getSell().getProductId().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getSellById(ArgumentMatchers.any(NonEmptyString.class))
        );
    }

    @Test
    void ifSellExistsItThrowsAnException() {

        Sell Sell = new Sell(
                new NonEmptyString("12345678"),
                new ProductId(12345678),
                new NonEmptyString("PRODUCT_NAME"),
                new NonEmptyPrice(100000.000),
                new NonEmptyTax(0.19),
                new NonEmptyCI(0),
                new NonEmptyString("CLIENT_NAME"),
                new NonEmptyString("CLIENT_LASTNAME"),
                new NonEmptyString("CLIENT_UBICATION"),
                new IdentificationNumber("12345678"),
                IdentificationType.CC,
                StateSell.Registrada
        );

        SellRepository repository = Mockito.mock(SellRepository.class);
        Mockito.when(repository.getSellById(ArgumentMatchers.any(NonEmptyString.class)));

        CreateSellService createSellService = new CreateSellService(repository);
        CreateSellRequest request = new CreateSellRequest(
                Sell.getidSell().getValue(),
                Sell.getProductId().getValue(),
                Sell.getProductName().getValue(),
                Sell.getProductPrice().getValue(),
                Sell.getProductTax().getValue(),
                Sell.getProductCI().getValue(),
                Sell.getclientName().getValue(),
                Sell.getclientLastName().getValue(),
                Sell.getclientUbication().getValue(),
                Sell.getidClient().getValue(),
                Sell.getIdentificationType().name(),
                Sell.getStateSell().name()
        );

        assertAll(
                () -> assertThrows(ProductAlredyExist.class, () -> createSellService.execute(request)),
                () -> Mockito.verify(repository, Mockito.times(0))
                        .storeSell(ArgumentMatchers.any(Sell.class))
        );

    }
}
