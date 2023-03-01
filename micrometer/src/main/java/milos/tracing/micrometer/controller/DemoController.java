package milos.tracing.micrometer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DemoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * http://localhost:8080/hello
     */
    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        logger.info("MDC INFO {}", MDC.getCopyOfContextMap());
        return ResponseEntity.ok(MDC.getCopyOfContextMap().toString());
    }
}