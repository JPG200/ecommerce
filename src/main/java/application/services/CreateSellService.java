package application.services;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;
import application.domain.*;
import application.errors.InputDataError;
import application.errors.ProductAlredyExist;
import application.errors.SellAlredyExist;
import application.model.Create.CreateProductRequest;
import application.model.Create.CreateProductResponse;
import application.model.Create.CreateSellRequest;
import application.model.Create.CreateSellResponse;
import application.ports.in.CreateProductUseCase;
import application.ports.in.CreateSellUseCase;
import application.ports.out.SellRepository;

import java.util.Optional;

public class CreateSellService implements CreateSellUseCase {
        private final SellRepository repository;

    public CreateSellService(SellRepository repository) {
        this.repository = repository;
    }

    public CreateSellResponse execute(CreateSellRequest request){
        Sell sell = validateInput(request);
        NonEmptyString idNumber = sell.getidSell();
        Optional<Sell> sellById = repository.getSellById(idNumber);
        if (sellById.isPresent()) {
            throw new SellAlredyExist(idNumber);
        }

        repository.storeSell(sell);

        return new CreateSellResponse(sell);
    }

    private Sell validateInput(CreateSellRequest request) {
        try {

            StateSell state =  StateSell.valueOf(request.getStateSell());
            NonEmptyString clientName = new NonEmptyString(request.getClientName());
            NonEmptyString clientLastName = new NonEmptyString(request.getClientLastName());
            NonEmptyString clientUbication = new NonEmptyString(request.getubicationClient());
            IdentificationType idType = IdentificationType.valueOf(request.getidType());
            IdentificationNumber idClient = new IdentificationNumber(request.getIdNumberClient());
            NonEmptyString idSell = new NonEmptyString(request.getidSell());
            NonEmptyCI ProductCI = new NonEmptyCI(request.getProductCI());
            NonEmptyString ProductName = new NonEmptyString(request.getProductName());
            NonEmptyTax ProductTax = new NonEmptyTax(request.getProductTax());
            NonEmptyPrice ProductPrice = new NonEmptyPrice(request.getProductPrice());
            ProductId ProductId = new ProductId(request.getProductId());

            return new Sell(
                    idSell,
                    ProductId,
                    ProductName,
                    ProductPrice,
                    ProductTax,
                    ProductCI,
                    clientName,
                    clientLastName,
                    clientUbication,
                    idClient,
                    idType,
                    state
            );
        } catch (RuntimeException e) {
            throw new InputDataError(e.getMessage());
        }
    }
}
