package dev.karatay.jsonserver.mapper.apirecord;

import dev.karatay.jsonserver.api.response.record.RecordResponse;
import dev.karatay.jsonserver.domain.document.ApiRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiRecordMapper {

    RecordResponse toResponse(ApiRecord record);

}
