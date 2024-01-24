package it.fabrick.fabricktest.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import it.fabrick.fabricktest.client.FabrickRestClient;
import it.fabrick.fabricktest.client.responsewrapper.FabrickRestClientResponseWrapper;
import it.fabrick.fabricktest.config.properties.FabrickApiProperties;
import it.fabrick.fabricktest.rest.model.accountbalance.response.AccountBalance;
import it.fabrick.fabricktest.rest.model.response.FabrickApiResponse;
import it.fabrick.fabricktest.rest.model.response.error.FabrickApiError;
import it.fabrick.fabricktest.utils.JacksonUtils;
import it.fabrick.fabricktest.utils.ObjectMapperFactory;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("accounts")
//@ApiResponses()
public class AccountsRestController {

    private final FabrickApiProperties apiProperties;
    private final FabrickRestClient client;

    @Autowired
    public AccountsRestController(FabrickApiProperties apiProperties,
                                  FabrickRestClient client) {
        this.apiProperties = apiProperties;
        this.client = client;
    }

    @GetMapping(
            path = "/{accountId}/balance",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<?> getAccountBalance(
            @PathVariable("accountId") @NotBlank String accountId
    ) throws Exception {

        log.info(">>> [getAccountBalance][START]: 'accountId':'{}'", accountId);

        final String url = apiProperties.getBaseUrl() + apiProperties.getAccountBalancePath();

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("accountId", accountId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        final String uri = builder.buildAndExpand(urlParams).toUriString();

        final FabrickRestClientResponseWrapper responseWrapper = client.get(uri);
        final HttpStatus status = responseWrapper.getStatus();

        JavaType javaType = JacksonUtils.getJavaType(FabrickApiResponse.class, AccountBalance.class);
        FabrickApiResponse<AccountBalance> jsonResponse = JacksonUtils.deserializeJsonAs(responseWrapper.getResponseBody(), javaType);
        String responseStr;

        if (status.is2xxSuccessful()) {
            AccountBalance payload = jsonResponse.getPayload();
            responseStr = JacksonUtils.writeAsString(payload);
        }
        else {
            FabrickApiError error = jsonResponse.getErrors().get(0);
            responseStr = JacksonUtils.writeAsString(error);
        }

        log.info("<<< [getAccountBalance][END]: 'accountId':'{}'", accountId);

        return new ResponseEntity<>(responseStr, status);

    }

}
