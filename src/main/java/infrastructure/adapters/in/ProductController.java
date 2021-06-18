package infrastructure.adapters.in;

import application.model.Create.CreateProductRequest;
import application.model.List.ListProductRequest;
import application.ports.in.CreateProductUseCase;
import application.ports.in.ListProductUseCase;
import infrastructure.commons.UseCaseHttpExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping(path = "/ecommerce")
public class ProductController {
    private final UseCaseHttpExecutor useCaseHttpExecutor;
    private final CreateProductUseCase createProductUseCase;
    private final ListProductUseCase listProductUseCase;

    @Autowired
    public ProductController(UseCaseHttpExecutor useCaseHttpExecutor, CreateProductUseCase createProductUseCase, ListProductUseCase listProductUseCase) {
        this.useCaseHttpExecutor = useCaseHttpExecutor;
        this.createProductUseCase = createProductUseCase;
        this.listProductUseCase = listProductUseCase;
    }

    @GetMapping
    public ResponseEntity listProductHandler(
            @RequestParam(name = "limit", defaultValue = "10") String limit,
            @RequestParam(name = "skip", defaultValue = "0") String skip
    ) {
        Integer limitInt = Integer.parseInt(limit, 10);
        Integer skipInt = Integer.parseInt(skip, 10);
        return useCaseHttpExecutor.executeUseCase(
                listProductUseCase,
                new ListProductRequest(limitInt, skipInt)
        );
    }


    @PostMapping
    public ResponseEntity createProductHandler(
            @RequestBody CreateProductRequest request
    ) {
        return useCaseHttpExecutor.executeUseCase(
                createProductUseCase,
                request
        );
    }
}
