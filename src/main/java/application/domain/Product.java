package application.domain;

import application.commons.*;

public class Product {
private final ProductId ProductId;
private final NonEmptyString ProductName;
private final NonEmptyPrice ProductPrice;
private final NonEmptyTax ProductTax;
private final NonEmptyString ProductDescription;
private final ProductState ProductState;
private final NonEmptyCI ProductCI;


    public Product(ProductId productId, NonEmptyString productName, NonEmptyPrice productPrice, NonEmptyTax productTax, NonEmptyString productDescription, application.domain.ProductState productState, NonEmptyCI productCI) {
       Validate.notNull(productId,"Product ID can not be null");
       Validate.notNull(productName,"Product Name can not be null");
       Validate.notNull(productPrice,"Product Price can not be null");
       Validate.notNull(productCI,"Product Inventory can not be null");
       Validate.notNull(productDescription,"Product Description can not be null");
       Validate.notNull(productState,"Product State can not be null");
       Validate.notNull(productTax,"Product Tax can not be null");



        this.ProductId = productId;
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.ProductTax = productTax;
        this.ProductDescription = productDescription;
        this.ProductState = productState;
        this.ProductCI = productCI;
    }

    public NonEmptyString getProductDescription() { return ProductDescription; }

    public NonEmptyCI getProductCI() { return ProductCI; }

    public NonEmptyString getProductName() { return ProductName; }

    public ProductId getProductId(){return ProductId;}

    public NonEmptyPrice getProductPrice() {return ProductPrice; }

    public ProductState getProductState() {return ProductState; }

    public NonEmptyTax getProductTax() { return ProductTax; }
}
