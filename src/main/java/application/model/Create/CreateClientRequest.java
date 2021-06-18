package application.model.Create;

import application.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateClientRequest implements ApplicationRequest {
    private String clientName;
    private String clientLastName;
    private String clientUbication;
    private String idClient;
    private String idType;

    public CreateClientRequest(String clientName, String clientLastName, String clientUbication, String idClient, String idType) {
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.clientUbication = clientUbication;
        this.idClient = idClient;
        this.idType = idType;
    }

    public String getubicationClient(){return this.clientUbication;}
    public String getClientName(){return this.clientName;}
    public String getClientLastName(){return this.clientLastName;}
    public String getidType(){return this.idType;}
    public String getIdNumberClient(){return this.idClient;}

    public void getClientName(String value){this.clientName=value;}
    public void getClientLastName(String value){this.clientLastName=value;}
    public void getidType(String value){this.idType=value;}
    public void getIdNumberClient(String value){this.idClient=value;}
    public void getubicationClient(String value){this.clientUbication=value;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateClientRequest that = (CreateClientRequest) o;
        return  Objects.equals(clientName,that.clientName) && Objects.equals(clientLastName,that.clientLastName)
                && Objects.equals(idType,that.idType) && Objects.equals(idClient,that.idClient) && Objects.equals(clientUbication,that.clientUbication)
               ;
    }

    @Override
    public String toString() {
        return "CreateSellRequest{" +
                ", clientName='" + clientName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", idType='" + idType + '\'' +
                ", idClient='" + idClient + '\'' +
                ", clientUbication='" + clientUbication + '\'' +
                '}';
    }
}
