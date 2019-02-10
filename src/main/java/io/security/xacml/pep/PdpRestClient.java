package io.security.xacml.pep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Collections.singletonList;

@Service
public class PdpRestClient {

    private static final MediaType APPLICATION_XACML_AND_JSON = MediaType
            .parseMediaType("application/xacml+json");
    private final String url;
    private final RestTemplate restTemplate;

    public PdpRestClient(@Value("${pdp.url}") String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    PdpResponse postPdpRequest(PdpRequest pdpRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_XACML_AND_JSON);
        headers.setAccept(singletonList(APPLICATION_XACML_AND_JSON));
        HttpEntity<?> httpRequest = new HttpEntity<>(pdpRequest, headers);
        try {
            ResponseEntity<PdpResponse> responseEntity = restTemplate.postForEntity(url, httpRequest, PdpResponse.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e){
            throw new ResponseStatusException(e.getStatusCode(), String.format("request to pdp failed with status=%s and body=%s",e.getStatusText(), e.getResponseBodyAsString()), e);
        }

    }
}
