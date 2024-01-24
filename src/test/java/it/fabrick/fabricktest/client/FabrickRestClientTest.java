package it.fabrick.fabricktest.client;

import it.fabrick.fabricktest.client.responsewrapper.FabrickRestClientResponseWrapper;
import it.fabrick.fabricktest.common.FabrickSpringBootTest;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@FabrickSpringBootTest
public class FabrickRestClientTest {

    @Mock
    private HttpClientBuilder httpClientBuilder;
    @Mock
    private CloseableHttpClient httpClient;
    @Mock
    private CloseableHttpResponse httpResponse;
    @Mock
    private StatusLine statusLine;
    @Mock
    private HttpEntity httpEntity;
    //@Mock
    //private ApiProperties apiProperties;

    @InjectMocks
    private FabrickRestClient fabrickRestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(httpClientBuilder.build()).thenReturn(httpClient);
    }

    @Test
    void getShouldReturnSuccessResponse() throws Exception {

    }

}
