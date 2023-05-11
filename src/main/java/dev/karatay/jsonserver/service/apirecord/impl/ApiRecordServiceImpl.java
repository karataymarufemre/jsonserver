package dev.karatay.jsonserver.service.apirecord.impl;

import dev.karatay.jsonserver.domain.document.ApiRecord;
import dev.karatay.jsonserver.repository.ApiRecordRepository;
import dev.karatay.jsonserver.service.apirecord.ApiRecordService;
import dev.karatay.jsonserver.service.jsonparser.JsonParserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiRecordServiceImpl implements ApiRecordService {

    private final ApiRecordRepository apiRecordRepository;
    private final JsonParserService jsonParserService;

    @Override
    public String getRawRecordById(String namespace, String domain, String id) {
        String jsonListString = getRecordRaw(namespace + "/" + domain);
        JSONArray jsonArray = new JSONArray(jsonListString);
        return jsonParserService.findByIdInJsonArray(jsonArray, id).toString();
    }

    @Override
    public String getRawRecordList(String namespace, String domain, Map<String, String[]> parameterMap) {
        var jsonData = apiRecordRepository.findByNamespaceAndDomain(namespace, domain).map(ApiRecord::getJsonData).orElseThrow(()-> new RuntimeException("record not found"));
        JSONArray jsonArray = new JSONArray(jsonData);
        for(Map.Entry<String, String[]> entry: parameterMap.entrySet())
            jsonArray = jsonParserService.findByKeyEqualsArray(jsonArray, entry.getKey(), entry.getValue()[0]);
        return jsonArray.toString();
    }

    @Override
    public String getRawRecordList(String namespace) {
        JSONObject jsonObject = new JSONObject();
        apiRecordRepository.findByNamespace(namespace).forEach(d-> jsonObject.put(d.getDomain(), new JSONArray(d.getJsonData())) );
        return jsonObject.toString();
    }

    @Override
    public String addRecord(String namespace, String domain, String jsonData) {
        var record = apiRecordRepository.findByNamespaceAndDomain(namespace, domain);
        if(record.isEmpty()) {
            ApiRecord apiRecord = new ApiRecord();
            apiRecord.setId(namespace + "/" + domain);
            apiRecord.setNamespace(namespace);
            apiRecord.setDomain(domain);
            apiRecord.setJsonData(new JSONArray(List.of(new JSONObject(jsonData))).toString());
            return apiRecordRepository.save(apiRecord).getJsonData();
        }
        JSONArray jsonArray = new JSONArray(record.get().getJsonData());
        jsonArray.put(new JSONObject(jsonData));
        record.get().setJsonData(jsonArray.toString());
        return apiRecordRepository.save(record.get()).getJsonData();
    }

    @Override
    public String updateRecordById(String namespace, String domain, String id, String jsonData) {
        var record = apiRecordRepository.findById(namespace + "/" + domain).orElseThrow(()-> new RuntimeException("record not found!"));
        JSONArray jsonArray = new JSONArray(record.getJsonData());
        var index = jsonParserService.findIndexByIdInJsonArray(jsonArray, id).orElseThrow(()-> new RuntimeException("record not found!"));
        jsonArray.put(index, new JSONObject(jsonData));
        record.setJsonData(jsonArray.toString());
        return apiRecordRepository.save(record).getJsonData();
    }

    @Override
    public String deleteRecordById(String namespace, String domain, String id) {
        var record = apiRecordRepository.findById(namespace + "/" + domain).orElseThrow(()-> new RuntimeException("record not found!"));
        JSONArray jsonArray = new JSONArray(record.getJsonData());
        var index = jsonParserService.findIndexByIdInJsonArray(jsonArray, id).orElseThrow(()-> new RuntimeException("record not found!"));
        jsonArray.remove(index);
        record.setJsonData(jsonArray.toString());
        return apiRecordRepository.save(record).getJsonData();
    }


    public String getRecordRaw(String id) {
        return apiRecordRepository.findById(id).map(ApiRecord::getJsonData).orElseThrow(()-> new RuntimeException("record not found"));
    }

}
