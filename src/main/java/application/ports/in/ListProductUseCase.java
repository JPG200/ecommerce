package application.ports.in;

import application.commons.operation.ApplicationUseCase;
import application.model.List.ListProductRequest;
import application.model.List.ListProductResponse;

public interface ListProductUseCase extends ApplicationUseCase<ListProductRequest, ListProductResponse> {
}
