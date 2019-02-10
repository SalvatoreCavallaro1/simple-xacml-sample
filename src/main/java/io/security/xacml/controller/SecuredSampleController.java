package io.security.xacml.controller;

import io.security.xacml.pep.Pep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SecuredSampleController {

    private final Pep pep;

    public SecuredSampleController(Pep pep) {
        this.pep = pep;
    }

    @GetMapping("/secured")
    public String getSampleResource(@RequestParam("resource") String resource){
        log.info("received GET request for resourceId={}", resource);
        pep.evaluateAccess("read", resource );
        return "This is resource with resourceId=" + resource;
    }
}
