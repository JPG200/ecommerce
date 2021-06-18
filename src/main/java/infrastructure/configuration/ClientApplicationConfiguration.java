package infrastructure.configuration;

import application.ports.in.CreateClientUseCase;
import application.ports.in.ListClientUseCase;
import application.ports.out.ClientRepository;
import application.services.CreateClientService;
import application.services.ListClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientApplicationConfiguration {
    @Bean
    public CreateClientUseCase createClientServiceBean(
            ClientRepository ClientRepository
    ) {
        return new CreateClientService(ClientRepository);
    }

    @Bean
    public ListClientUseCase listClientUseCase(
            @Qualifier("sql") ClientRepository repository
    ) {
        return new ListClientService(repository);
    }
}
