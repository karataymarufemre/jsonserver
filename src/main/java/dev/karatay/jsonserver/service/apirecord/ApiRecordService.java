package dev.karatay.jsonserver.service.apirecord;

import jakarta.servlet.http.HttpServletRequest;

public interface ApiRecordService {
    String getRawRecordById(String namespace, String domain, String id);
    String getRawRecordList(String namespace, String domain, HttpServletRequest request);
    String getRawRecordList(String namespace);
    String addRecord(String namespace, String domain, String jsonData);
    String updateRecordById(String namespace, String domain, String id, String jsonData);
    String deleteRecordById(String namespace, String domain, String id);
}
