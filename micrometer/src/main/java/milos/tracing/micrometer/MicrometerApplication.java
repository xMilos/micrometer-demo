package milos.tracing.micrometer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Micrometer application.
 */
@SpringBootApplication
public class MicrometerApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MicrometerApplication.class, args);
    }

}