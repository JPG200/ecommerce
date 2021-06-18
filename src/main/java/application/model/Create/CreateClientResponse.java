package application.model.Create;

import application.commons.operation.ApplicationResponse;
;
import application.domain.Client;


public class CreateClientResponse implements ApplicationResponse {
    private final Client ClientObj;

    public CreateClientResponse(Client ClientObj) {
        this.ClientObj = ClientObj;
    }

    public Client getClient() {
        return ClientObj;
    }

    @Override
    public String toString() {
        return "CreateClientResponse{" +
                "Client= " + ClientObj +
                '}';
    }
}
