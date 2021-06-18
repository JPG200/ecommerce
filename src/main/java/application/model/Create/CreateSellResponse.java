package application.model.Create;


import application.commons.operation.ApplicationResponse;
import application.domain.Sell;

public class CreateSellResponse implements ApplicationResponse {
    private final Sell sell;

    public CreateSellResponse(Sell sell) {
        this.sell = sell;
    }

    public Sell getSell(){return this.sell;}

    @Override
    public String toString() {
        return "CreateSellResponse{" +
                "sell=" + sell +
                '}';
    }

}
