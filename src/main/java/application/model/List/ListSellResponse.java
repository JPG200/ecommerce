package application.model.List;

import application.commons.operation.ApplicationResponse;
import application.domain.Product;
import application.domain.Sell;

import java.util.Collection;

import java.util.Collection;

public class ListSellResponse implements ApplicationResponse{
    private final Collection<Sell> items;
    private final Integer total;


    public ListSellResponse(Collection<Sell> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public Collection<Sell> getItems(){return items;}

    public Integer getTotal(){return total;}
}
