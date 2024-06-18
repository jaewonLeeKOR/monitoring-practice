package monitoring.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class DemoResponse {
    @Getter
    @AllArgsConstructor
    public static class GetDemoResponse {
        private final String message;
    }


    @Getter
    @AllArgsConstructor
    public static class PostDemoResponse {
        private final String message;
    }
}
