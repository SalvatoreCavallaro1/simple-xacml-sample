package io.security.xacml.pep;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * XACML 3.0 JSON Profile includes more properties and objects than implemented in this class,
 * implement those as needed.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdpResponse {
  @JsonProperty("Response")
  private List<Result> result = new ArrayList<>();

  public Decision getDecision(){
    return result.stream().findFirst()
        .map(Result::getDecision)
        .orElse(Decision.Indeterminate);
  }

  public Optional<JsonNode> getAdvice(){
    return result.stream().findFirst()
        .map(Result::getAssociatedAdvice);
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Result {

    @JsonProperty("Decision")
    private String responseDecision;
    @JsonProperty("Status")
    private JsonNode status;
    @JsonProperty("AssociatedAdvice")
    private JsonNode associatedAdvice;
    @JsonProperty("Obligations")
    private JsonNode obligations;

    Decision getDecision(){
      return Decision.valueOf(responseDecision);
    }

  }

public enum Decision {
    Permit,
    Deny,
    NotApplicable,
    Indeterminate
}
}


