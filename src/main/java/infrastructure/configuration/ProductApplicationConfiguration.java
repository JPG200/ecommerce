package infrastructure.configuration;

import application.ports.in.CreateProductUseCase;
import application.ports.in.ListProductUseCase;
import application.ports.out.ProductRepository;
import application.services.CreateProductService;
import application.services.ListProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductApplicationConfiguration {
    @Bean
    public CreateProductUseCase createProductServiceBean(
            ProductRepository ProductRepository
    ) {
        return new CreateProductService(ProductRepository);
    }

    @Bean
    public ListProductUseCase listProductUseCase(
            @Qualifier("sql") ProductRepository repository
    ) {
        return new ListProductService(repository);
    }
}
