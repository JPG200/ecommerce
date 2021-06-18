package application.model.Create;

import application.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateProductRequest implements ApplicationRequest {
    private int productId;
    private String ProductName;
    private double ProductPrice;
    private double ProductTax;
    private String ProductDescription;
    private String ProductState;
    private int ProductCI;

    public CreateProductRequest(int productId, String productName, double productPrice, double productTax, String productDescription, String productState, int productCI) {
        this.productId = productId;
        this.ProductName = productName;
        this.ProductPrice = productPrice;
        this.ProductTax = productTax;
        this.ProductDescription = productDescription;
        this.ProductState = productState;
        this.ProductCI = productCI;
    }

    public int getProductId(){return this.productId;}

    public void setProductId(int value){this.productId=value;}

    public String getProductName(){return this.ProductName;}

    public void setName(String value){this.ProductName=value;}

    public double getProductPrice(){return ProductPrice;}

    public void setProductPrice(double value){this.ProductPrice=value;}

    public double getProductTax(){return this.ProductTax;}

    public void setProductTax(double value){this.ProductTax=value;}

    public String getProductDescription(){return this.ProductDescription;}

    public void setProductDescription(String value){this.ProductDescription=value;}

    public String getProductState(){return this.ProductState;}

    public void setProductState(String value){this.ProductState=value;}

    public int getProductCI(){return this.ProductCI;}

    public void setProductCI(int value){this.ProductCI=value;}

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass() != o.getClass())return false;
        CreateProductRequest that = (CreateProductRequest) o;
        return Objects.equals(productId, that.productId) && Objects.equals(ProductName, that.ProductName) &&
                Objects.equals(ProductPrice, that.ProductPrice) && Objects.equals(ProductTax, that.ProductTax) && Objects.equals(ProductDescription, that.ProductDescription) &&
                Objects.equals(ProductState, that.ProductState) && Objects.equals(ProductCI,that.ProductCI);
    }
    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name= ' " + ProductName + '\'' +
                ", Product Price= ' " + ProductPrice + '\'' +
                ", Product Tax= ' " + ProductTax + '\'' +
                ", Product Description= ' " + ProductDescription + '\'' +
                ", Product Id= ' " + productId + '\'' +
                ", Product State= ' " + ProductState + '\'' +
                ", Product In Inventory= ' " + ProductCI + '\'' +
                '}';
    }



}
