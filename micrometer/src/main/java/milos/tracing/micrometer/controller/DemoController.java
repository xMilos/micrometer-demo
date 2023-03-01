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
     * curl -H "x-b3-traceId: 66275a023f6eccfc" -H "x-b3-spanId: 4f5e6d7c8b9a0f1e" http://localhost:8080/hello
     * Response should be {traceId=66275a023f6eccfc, spanId=4f5e6d7c8b9a0f1e}
     * Instead is {traceId=66275a023f6eccfc, spanId=e08b8098bff7a7bd}
     */
    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        logger.info("MDC INFO {}", MDC.getCopyOfContextMap());
        return ResponseEntity.ok(MDC.getCopyOfContextMap().toString());
    }
}