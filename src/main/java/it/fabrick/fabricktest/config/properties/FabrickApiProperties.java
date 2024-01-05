package it.fabrick.fabricktest.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@ConfigurationProperties(prefix = "fabrick-api")
@Validated
public class FabrickApiProperties {

    @NotBlank
    private String  baseUrl;
    @NotBlank
    private String  authSchema;
    @NotBlank
    private String  apiKey;
    @NotBlank
    private String  accountBalancePath;

}
