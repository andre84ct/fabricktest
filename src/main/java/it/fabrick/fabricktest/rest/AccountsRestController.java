package it.fabrick.fabricktest.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import it.fabrick.fabricktest.client.FabrickRestClient;
import it.fabrick.fabricktest.config.properties.FabrickApiProperties;
import it.fabrick.fabricktest.rest.model.accountbalance.response.AccountBalance;
import it.fabrick.fabricktest.rest.model.response.FabrickApiResponse;
import it.fabrick.fabricktest.utils.JacksonUtils;
import it.fabrick.fabricktest.utils.ObjectMapperFactory;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
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

        log.info(">>> [getAccountBalance][RX]: 'accountId':'{}'", accountId);

        String url = apiProperties.getBaseUrl() + apiProperties.getAccountBalancePath();

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("accountId", accountId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        String uri = builder.buildAndExpand(urlParams).toUriString();

        String responseStr = client.get(uri);

        JavaType javaType = JacksonUtils.getJavaType(FabrickApiResponse.class, AccountBalance.class);
        FabrickApiResponse<AccountBalance> jsonResponse = JacksonUtils.deserializeJsonAs(responseStr, javaType);

        AccountBalance payload = jsonResponse.getPayload();

        log.info("<<< [getAccountBalance][OK]: 'accountId':'{}'", accountId);

        return new ResponseEntity<>(payload, HttpStatus.OK);

    }

}
