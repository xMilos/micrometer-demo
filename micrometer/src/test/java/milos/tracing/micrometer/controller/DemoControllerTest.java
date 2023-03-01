package milos.tracing.micrometer.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHello() throws Exception {

        // create a trace and span id using Brave's SpanId and TraceId classes
        String spanId = "4f5e6d7c8b9a0f1e";
        String traceId = "66275a023f6eccfc";

        MDC.put("traceId", traceId);
        MDC.put("spanId", traceId);

        // add the trace and span id as headers to the request
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello")
                .header("X-B3-TraceId", traceId)
                .header("X-B3-SpanId", spanId);

        // perform the request and verify the response
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(traceId)))
                .andExpect(content().string(containsString(spanId)));
    }
}
