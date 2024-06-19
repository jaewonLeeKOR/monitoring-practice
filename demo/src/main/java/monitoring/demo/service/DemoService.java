package monitoring.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import monitoring.demo.dto.DemoRequest.PostDemoRequest;
import monitoring.demo.dto.DemoResponse.GetDemoResponse;
import monitoring.demo.dto.DemoResponse.PostDemoResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {
    public GetDemoResponse getDemo() {
        log.debug("service debug");
        log.error("service error");
        log.info("service info");
        log.trace("service trace");
        log.warn("service warn");
        return new GetDemoResponse("Hello, Demo!");
    }

    public PostDemoResponse postDemo(PostDemoRequest request) {
        return new PostDemoResponse(request.getMessage());
    }

    public String getError() throws RuntimeException {
        throw new RuntimeException("error");
    }
}
