package milos.tracing.micrometer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Micrometer application.
 */
@SpringBootApplication
public class MicrometerApplication {

    /**
     * The entry point of application.
     *
     * @param args
     *         the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MicrometerApplication.class, args);
    }

}

/**
 * The type Employee controller.
 */
@RestController
class DemoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * http://localhost:8080/hello
     *
     * header: [{"key":"traceparent","value":"00-4bf92f3577b34da6a3ce929d0e0e4736-66275a023f6eccfc-00","description":"","type":"default","enabled":true}]
     * Example response: {traceId=4bf92f3577b34da6a3ce929d0e0e4736, spanId=555e281a5f5666a9} note spanId is not the same as 66275a023f6eccfc
     */
    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        logger.info("MDC INFO {}", MDC.getCopyOfContextMap());
        return ResponseEntity.ok(MDC.getCopyOfContextMap().toString());
    }
}