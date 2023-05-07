package dev.karatay.jsonserver.repository;

import dev.karatay.jsonserver.domain.document.ApiRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ApiRecordRepository
        extends MongoRepository<ApiRecord, String> {

    Optional<ApiRecord> findByNamespaceAndDomain(String namespace, String domain);
    List<ApiRecord> findByNamespace(String namespace);
}
