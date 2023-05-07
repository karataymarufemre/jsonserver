package dev.karatay.jsonserver.domain.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ApiRecord {
    @Id
    private String id; // namespace/domain
    @Indexed
    private String namespace;
    private String domain;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String jsonData; //should be a list like [{"id": 1, "name": "Jon"},{}]
}
