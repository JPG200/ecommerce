package application.services;

import application.domain.Product;
import application.domain.Sell;
import application.model.List.ListProductRequest;
import application.model.List.ListProductResponse;
import application.model.List.ListSellRequest;
import application.model.List.ListSellResponse;
import application.ports.in.ListProductUseCase;
import application.ports.in.ListSellUseCase;
import application.ports.out.ProductRepository;
import application.ports.out.SellRepository;

import java.util.Collection;

public class ListProductService implements ListProductUseCase {
    private final ProductRepository repository;

    public ListProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListProductResponse execute(ListProductRequest request) {
        Collection<Product> Product = repository.listProduct(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = repository.countProduct();
        return new ListProductResponse(Product, total);
    }
}
