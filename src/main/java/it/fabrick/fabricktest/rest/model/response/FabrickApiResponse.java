package it.fabrick.fabricktest.rest.model.response;

import it.fabrick.fabricktest.rest.model.response.error.FabrickApiError;
import lombok.Data;

import java.util.List;

@Data
public class FabrickApiResponse<T> {

    private String status;
    private List<FabrickApiError> errors;
    private T payload;

}
