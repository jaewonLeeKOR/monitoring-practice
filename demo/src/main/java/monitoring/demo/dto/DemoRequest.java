package monitoring.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class DemoRequest {
    @Getter
    @NoArgsConstructor
    public static class PostDemoRequest {
        private String message;
    }
}
