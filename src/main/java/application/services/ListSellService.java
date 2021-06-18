package application.services;

import application.domain.Sell;
import application.model.List.ListSellRequest;
import application.model.List.ListSellResponse;
import application.ports.in.ListSellUseCase;
import application.ports.out.SellRepository;
import java.util.Collection;

public class ListSellService implements ListSellUseCase {
    private final SellRepository repository;

    public ListSellService(SellRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListSellResponse execute(ListSellRequest request) {
        Collection<Sell> Sell = repository.listSell(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = repository.countSell();
        return new ListSellResponse(Sell, total);
    }
}
