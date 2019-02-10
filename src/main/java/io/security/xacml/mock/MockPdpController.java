package io.security.xacml.mock;

import io.security.xacml.pep.PdpRequest;
import io.security.xacml.pep.PdpResponse;
import io.security.xacml.pep.attributes.StandardAttributes;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Profile("mock")
@RestController
public class MockPdpController {

    private static final String PERMIT_FOR_SUBJECT = "success";

    @PostMapping("/authorize")
    public PdpResponse authorize(@RequestBody PdpRequest pdpRequest) {
        return PdpResponse.builder()
            .result(Collections.singletonList(PdpResponse.Result.builder()
                .responseDecision(isPermit(pdpRequest) ?
                    PdpResponse.Decision.Permit.name() : PdpResponse.Decision.Deny.name())
                .build()))
            .build();
    }

    private boolean isPermit(PdpRequest request) {
        return request.getRequest().getAccessSubject().getAttributes().stream()
            .filter(attribute -> attribute.getAttributeId().equals(StandardAttributes.SUBJECT_ID))
            .anyMatch(attribute -> attribute.getValue().equalsIgnoreCase(PERMIT_FOR_SUBJECT));
    }
}
