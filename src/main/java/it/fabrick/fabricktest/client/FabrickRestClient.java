package it.fabrick.fabricktest.client;

import it.fabrick.fabricktest.client.responsewrapper.FabrickRestClientResponseWrapper;
import it.fabrick.fabricktest.config.properties.FabrickApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FabrickRestClient {

    private final FabrickApiProperties apiProperties;

    @Autowired
    public FabrickRestClient(FabrickApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    public FabrickRestClientResponseWrapper get(String uri) throws Exception {
        // =============================================
        // = POST Request Setup:
        // =============================================
        //HttpGet getRequest = new HttpGet("https://" + uri + id);
        HttpGet getRequest = new HttpGet(uri);
        getRequest.setHeader("Auth-Schema", apiProperties.getAuthSchema());
        getRequest.setHeader("Api-Key", apiProperties.getApiKey());

        final RequestLine requestLine = getRequest.getRequestLine();
        log.info(">>> [FABRICK][API][GET]: {}", requestLine);

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(getRequest)) {

            final StatusLine statusLine = response.getStatusLine();
            final int statusCode = statusLine.getStatusCode();
            final HttpStatus httpStatus = HttpStatus.valueOf(statusCode);

                    log.info("<<< [FABRICK][API][GET][Response]: {}", statusLine);

            /*if (!HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                throw new Exception("[FABRICK][API][GET][KO]: " + statusLine + "\n" + requestLine);
            }*/

            HttpEntity responseBody = response.getEntity();

            FabrickRestClientResponseWrapper restClientResponseWrapper = FabrickRestClientResponseWrapper.builder()
                    .status(httpStatus)
                    .responseBody(EntityUtils.toString(responseBody))
                    .build();
            return restClientResponseWrapper;
        }

    }

    public String post(String uri, String body) throws Exception {
        // =============================================
        // = POST Request Setup:
        // =============================================
        HttpPost postRequest = new HttpPost("https://" + uri);
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("Auth-Schema", apiProperties.getAuthSchema());
        postRequest.setHeader("Api-Key", apiProperties.getAuthSchema());
        postRequest.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));

        final RequestLine requestLine = postRequest.getRequestLine();
        log.info(">>> [FABRICK][API][POST]: {}", requestLine);

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(postRequest)) {

            final StatusLine statusLine = response.getStatusLine();

            if (!HttpStatus.valueOf(statusLine.getStatusCode()).is2xxSuccessful()) {
                throw new Exception("[FABRICK][API][POST][KO]: " + statusLine + "\n" + requestLine + "\n" + body);
            }

            HttpEntity responseBody = response.getEntity();

            return EntityUtils.toString(responseBody);
        }

    }
}
