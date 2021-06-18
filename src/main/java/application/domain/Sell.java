package application.domain;

import application.commons.NonEmptyCI;
import application.commons.NonEmptyPrice;
import application.commons.NonEmptyString;
import application.commons.NonEmptyTax;

public class Sell {
    private final NonEmptyString idSell;
    private final ProductId ProductId;
    private final NonEmptyString ProductName;
    private final NonEmptyPrice ProductPrice;
    private final NonEmptyTax ProductTax;
    private final NonEmptyCI ProductCI;
    private final NonEmptyString clientName;
    private final NonEmptyString clientLastName;
    private final NonEmptyString clientUbication;
    private final IdentificationNumber idClient;
    private final IdentificationType idType;
    private final StateSell state;

    public Sell(NonEmptyString idSell, application.domain.ProductId productId, NonEmptyString productName, NonEmptyPrice productPrice, NonEmptyTax productTax, NonEmptyCI productCI, NonEmptyString clientName, NonEmptyString clientLastName, NonEmptyString clientUbication, IdentificationNumber idClient, IdentificationType idType, StateSell state) {
        this.idSell = idSell;
        this.ProductId = productId;
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.ProductTax = productTax;
        this.ProductCI = productCI;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.clientUbication = clientUbication;
        this.idClient = idClient;
        this.idType = idType;
        this.state = state;
    }

    public StateSell getStateSell(){return this.state;}
    public NonEmptyString getidSell(){return this.idSell;}
    public NonEmptyString getclientName(){return this.clientName;}
    public NonEmptyString getclientLastName(){return this.clientLastName;}
    public NonEmptyString getclientUbication(){return this.clientUbication;}
    public IdentificationNumber getidClient(){return this.idClient;}
    public IdentificationType getIdentificationType(){return this.idType;}
    public NonEmptyCI getProductCI() { return ProductCI; }
    public NonEmptyString getProductName() { return ProductName; }
    public ProductId getProductId(){return ProductId;}
    public NonEmptyPrice getProductPrice() {return ProductPrice; }
    public NonEmptyTax getProductTax() { return ProductTax; }
}
