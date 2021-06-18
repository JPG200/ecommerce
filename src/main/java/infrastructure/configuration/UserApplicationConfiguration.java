package infrastructure.configuration;

import application.ports.in.CreateUserUseCase;
import application.ports.in.ListUserUseCase;
import application.ports.out.UserRepository;
import application.services.CreateUserService;
import application.services.ListUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserApplicationConfiguration {

    @Bean
    public CreateUserUseCase createUserServiceBean(
            UserRepository UserRepository
    ) {
        return (CreateUserUseCase) new CreateUserService(UserRepository);
    }

    @Bean
    public ListUserUseCase listClientUseCase(
            @Qualifier("sql") UserRepository repository
    ) {
        return new ListUserService(repository);
    }
}
