package dev.karatay.jsonserver.api.response.record;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordResponse {
    private String id;
    private String projectName;
    private String username;
    private String path;
    private Boolean isSecure;
    private LocalDateTime createdAt;
    @JsonRawValue
    private String jsonData;
}
