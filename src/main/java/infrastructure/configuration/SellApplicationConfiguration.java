package infrastructure.configuration;

import application.ports.in.CreateProductUseCase;
import application.ports.in.CreateSellUseCase;
import application.ports.in.ListProductUseCase;
import application.ports.in.ListSellUseCase;
import application.ports.out.ProductRepository;
import application.ports.out.SellRepository;
import application.services.CreateProductService;
import application.services.CreateSellService;
import application.services.ListProductService;
import application.services.ListSellService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class SellApplicationConfiguration {
    @Bean
    public CreateSellUseCase createSellServiceBean(
            SellRepository SellRepository
    ) {
        return new CreateSellService(SellRepository);
    }

    @Bean
    public ListSellUseCase listSellUseCase(
            @Qualifier("sql") SellRepository repository
    ) {
        return new ListSellService(repository);
    }
}
