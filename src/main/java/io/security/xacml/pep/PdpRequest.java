package io.security.xacml.pep;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PdpRequest {
  @JsonProperty("Request")
  @JsonAlias({"request", "Request"})
  private Request request;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Request{

    @JsonProperty("AccessSubject")
    private Category accessSubject;
    @JsonProperty("Environment")
    private Category environment;
    @JsonProperty("Action")
    private Category action;
    @JsonProperty("Resource")
    private Category resource;
    
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {

      @JsonProperty("Attribute")
      @Singular
      private List<Attribute> attributes;
    }
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Attribute {
    @JsonProperty("AttributeId")
    private String attributeId;
    @JsonProperty("Value")
    private String value;
  }
}
