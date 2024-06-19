package monitoring.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class DemoRequest {
    @Getter
    @NoArgsConstructor
    @ToString
    public static class PostDemoRequest {
        private String message;
    }
}
