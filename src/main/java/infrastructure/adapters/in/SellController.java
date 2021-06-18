package infrastructure.adapters.in;


import application.model.Create.CreateSellRequest;
import application.model.List.ListSellRequest;
import application.ports.in.CreateSellUseCase;
import application.ports.in.ListSellUseCase;
import infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/ecommerce")
public class SellController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateSellUseCase createSellUseCase;
    private final ListSellUseCase listSellUseCase;


    @Autowired
    public SellController(UseCaseHttpExecutor useCaseHttpExecutor, CreateSellUseCase createSellUseCase, ListSellUseCase listSellUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createSellUseCase = createSellUseCase;
        this.listSellUseCase = listSellUseCase;
    }


    @GetMapping
    public ResponseEntity listSellHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listSellUseCase,
                new ListSellRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createSellHandler(
            @RequestBody CreateSellRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createSellUseCase,
                request
        );
    }
}
