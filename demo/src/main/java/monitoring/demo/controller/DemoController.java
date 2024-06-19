package monitoring.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import monitoring.demo.dto.DemoRequest.PostDemoRequest;
import monitoring.demo.dto.DemoResponse.GetDemoResponse;
import monitoring.demo.dto.DemoResponse.PostDemoResponse;
import monitoring.demo.service.DemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
@Slf4j
public class DemoController {
    private final DemoService demoService;

    @GetMapping
    public GetDemoResponse GetDemo() {
        log.debug("controller debug");
        log.error("controller error");
        log.info("controller info");
        log.trace("controller trace");
        log.warn("controller warn");
        return demoService.getDemo();
    }

    @PostMapping
    public PostDemoResponse PostDemo(@RequestBody PostDemoRequest request) {
        return demoService.postDemo(request);
    }

    @GetMapping("/error")
    public String GetError() {
        try {
            return demoService.getError();
        } catch(RuntimeException e) {}
        return "error handled!!";
    }
}
