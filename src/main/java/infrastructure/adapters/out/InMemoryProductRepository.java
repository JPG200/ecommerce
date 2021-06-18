package infrastructure.adapters.out;

import application.commons.NonEmptyString;
import application.domain.Client;
import application.domain.IdentificationNumber;
import application.domain.Product;
import application.domain.ProductId;
import application.ports.out.ProductRepository;
import application.ports.out.UserRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<ProductId, Product> database = new HashMap<>();

    @Override
    public Optional<Product> getProductByName(NonEmptyString NonEmptyString) {
        Product Product = database.get(NonEmptyString.getValue());
        return Optional.ofNullable(Product);
    }

    @Override
    public void updateProduct(Product product){database.replace(product.getProductId(),product);}

    @Override
    public void deleteProduct(Product product){database.remove(product.getProductId(), product);}

    @Override
    public void storeProduct(Product Product) {
        database.put(Product.getProductId(), Product);
    }


    @Override
    public Collection<Product> listProduct(int limit, int skip) {
        return database.values();
    }

    @Override
    public Integer countProduct() {
        return database.size();
    }
}
