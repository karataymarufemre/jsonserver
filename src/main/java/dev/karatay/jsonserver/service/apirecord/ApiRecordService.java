package dev.karatay.jsonserver.service.apirecord;

import java.util.Map;

public interface ApiRecordService {
    String getRawRecordById(String namespace, String domain, String id);
    String getRawRecordList(String namespace, String domain, Map<String, String[]> parameterMap);
    String getRawRecordList(String namespace);
    String addRecord(String namespace, String domain, String jsonData);
    String updateRecordById(String namespace, String domain, String id, String jsonData);
    String deleteRecordById(String namespace, String domain, String id);
}
