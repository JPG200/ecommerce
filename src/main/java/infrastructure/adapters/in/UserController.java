package infrastructure.adapters.in;

import application.model.Create.CreateUserRequest;
import application.model.List.ListUserRequest;
import application.ports.in.CreateUserUseCase;
import application.ports.in.ListUserUseCase;
import infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/ecommerce")
public class UserController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateUserUseCase CreateUserUseCase;
    private final ListUserUseCase listUserUseCase;

    @Autowired
    public UserController(UseCaseHttpExecutor useCaseHttpExecutor, CreateUserUseCase CreateUserUseCase, ListUserUseCase listUserUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.CreateUserUseCase = CreateUserUseCase;
        this.listUserUseCase = listUserUseCase;
    }
    @GetMapping
    public ResponseEntity listUserHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listUserUseCase,
                new ListUserRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createUserHandler(
            @RequestBody CreateUserRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                CreateUserUseCase,
                request
        );
    }
}
