package application.model.List;

import application.commons.operation.ApplicationRequest;

public class ListSellRequest implements ApplicationRequest {
    private final Integer limit;
    private final Integer skip;

    public ListSellRequest(Integer limit, Integer skip) {
        this.limit = limit;
        this.skip = skip;
    }

    public Integer getLimit(){return limit;}
    public Integer getSkip(){return skip;}
}
