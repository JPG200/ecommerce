package application.model.Create;

import application.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateSellRequest implements ApplicationRequest {
    private String idSell;
    private int ProductId;
    private String ProductName;
    private double ProductPrice;
    private double ProductTax;
    private int ProductCI;
    private String clientName;
    private String clientLastName;
    private String clientUbication;
    private String idClient;
    private String idType;
    private String state;

    public CreateSellRequest(int productId,String idSell, String productName, double productPrice, double productTax, int productCI, String clientName, String clientLastName, String clientUbication, String idClient, String idType, String state) {
        this.idSell = idSell;
        this.ProductId=productId;
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

    public String getidSell(){return this.idSell;}
    public int getProductId(){return this.ProductId;}
    public int getProductCI(){return this.ProductCI;}
    public String getProductName(){return this.ProductName;}
    public String getClientName(){return this.clientName;}
    public String getClientLastName(){return this.clientLastName;}
    public String getidType(){return this.idType;}
    public String getIdNumberClient(){return this.idClient;}
    public String getubicationClient(){return this.clientUbication;}
    public String getStateSell(){return this.state;}
    public double getProductPrice(){return this.ProductPrice;}
    public double getProductTax(){return this.ProductTax;}



    public void getidSell(String value){this.idSell=value;}
    public void getProductId(int value){this.ProductId=value;}
    public void getProductCI(int value){this.ProductCI=value;}
    public void getProductName(String value){this.ProductName=value;}
    public void getClientName(String value){this.clientName=value;}
    public void getClientLastName(String value){this.clientLastName=value;}
    public void getidType(String value){this.idType=value;}
    public void getIdNumberClient(String value){this.idClient=value;}
    public void getubicationClient(String value){this.clientUbication=value;}
    public void getStateSell(String value){this.state=value;}
    public void getProductPrice(double value){this.ProductPrice=value;}
    public void getProductTax(double value){this.ProductTax=value;}




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateSellRequest that = (CreateSellRequest) o;
        return Objects.equals(idSell, that.idSell) && Objects.equals(ProductId, that.ProductId) &&
                Objects.equals(ProductCI, that.ProductCI) && Objects.equals(ProductName, that.ProductName)
                && Objects.equals(clientName,that.clientName) && Objects.equals(clientLastName,that.clientLastName)
                && Objects.equals(idType,that.idType) && Objects.equals(idClient,that.idClient) && Objects.equals(clientUbication,that.clientUbication)
                && Objects.equals(state,that.state) && Objects.equals(ProductPrice,that.ProductPrice) && Objects.equals(ProductTax,that.ProductTax);
    }

    @Override
    public String toString() {
        return "CreateSellRequest{" +
                "idSell='" + idSell + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", ProductCI='" + ProductCI + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", idType='" + idType + '\'' +
                ", idClient='" + idClient + '\'' +
                ", clientUbication='" + clientUbication + '\'' +
                ", state='" + state + '\'' +
                ", ProductPrice='" + ProductPrice + '\'' +
                ", ProductTax='" + ProductTax + '\'' +
                '}';
    }
}
