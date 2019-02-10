package io.security.xacml.pep;

import io.security.xacml.pep.PdpRequest.Attribute;
import io.security.xacml.pep.PdpRequest.Request;
import io.security.xacml.pep.PdpRequest.Request.Category;
import io.security.xacml.pep.attributes.StandardAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class Pep {

    private final PdpRestClient pdpRestClient;

    public Pep(PdpRestClient pdpRestClient) {
        this.pdpRestClient = pdpRestClient;
    }

    public void evaluateAccess(String action, String resourceId) {
        log.info("evaluate access for subject={}, action={}, resourceId={}", subject(), action, resourceId);
        PdpRequest pdpRequest =
            PdpRequest.builder()
                .request(Request.builder()
                    .accessSubject(Category.builder()
                        .attribute(new Attribute(StandardAttributes.SUBJECT_ID, subject()))
                        .build())
                    .action(Category.builder()
                        .attribute(new Attribute(StandardAttributes.ACTION_ID, action))
                        .build())
                    .resource(Category.builder()
                        .attribute(new Attribute(StandardAttributes.RESOURCE_ID, resourceId))
                        .build())
                    .build())
                .build();

        Optional.ofNullable(pdpRestClient.postPdpRequest(pdpRequest))
            .map(PdpResponse::getDecision)
            .filter(decision -> decision.equals(PdpResponse.Decision.Permit))
            .orElseThrow(()->
                new ResponseStatusException(HttpStatus.FORBIDDEN,"forbidden, you are not authorized for this operation."));
    }

    private static String subject(){
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getPrincipal)
            .map(String.class::cast)
            .orElse(null);
    }
}
