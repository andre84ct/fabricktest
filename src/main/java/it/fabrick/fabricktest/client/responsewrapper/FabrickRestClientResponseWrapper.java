package it.fabrick.fabricktest.client.responsewrapper;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class FabrickRestClientResponseWrapper {

    private HttpStatus status;
    private String responseBody;
}
