package application.ports.in;

import application.commons.operation.ApplicationUseCase;
import application.model.Create.CreateProductRequest;
import application.model.Create.CreateProductResponse;

public interface CreateProductUseCase extends ApplicationUseCase<CreateProductRequest, CreateProductResponse> {
}
