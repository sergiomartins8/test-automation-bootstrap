package io.company.utils.api.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Note that this uses lombok dependencies.
 * Thus, for this to work (at least on intellij) you have to download and install lombok plugin and enable annotation processing.
 * https://github.com/mplushnikov/lombok-intellij-plugin
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authentication {
    @JsonProperty("AccessToken")
    private String accessToken;

    @JsonProperty("RefreshToken")
    private String refreshToken;
}
