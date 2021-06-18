package infrastructure.adapters.in;


import application.model.Create.CreateClientRequest;
import application.model.List.ListClientRequest;
import application.ports.in.CreateClientUseCase;
import application.ports.in.ListClientUseCase;
import infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ecommerce")
public class ClientController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateClientUseCase createClientUseCase;
    private final ListClientUseCase listClientUseCase;

    @Autowired
    public ClientController(UseCaseHttpExecutor useCaseHttpExecutor, CreateClientUseCase createClientUseCase, ListClientUseCase listClientUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createClientUseCase = createClientUseCase;
        this.listClientUseCase = listClientUseCase;
    }

    @GetMapping
    public ResponseEntity listSClientHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listClientUseCase,
                new ListClientRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createClientHandler(
            @RequestBody CreateClientRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createClientUseCase,
                request
        );
    }
}
