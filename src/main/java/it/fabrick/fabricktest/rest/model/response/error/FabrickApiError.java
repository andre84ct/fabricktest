package it.fabrick.fabricktest.rest.model.response.error;

import lombok.Data;

@Data
public class FabrickApiError {

    private String code;
    private String description;
    private String params;

}
