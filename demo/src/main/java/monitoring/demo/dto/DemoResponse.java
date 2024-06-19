package monitoring.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class DemoResponse {
    @Getter
    @AllArgsConstructor
    @ToString
    public static class GetDemoResponse {
        private final String message;
    }


    @Getter
    @AllArgsConstructor
    @ToString
    public static class PostDemoResponse {
        private final String message;
    }
}
