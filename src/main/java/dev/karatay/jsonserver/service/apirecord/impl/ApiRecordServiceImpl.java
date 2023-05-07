package dev.karatay.jsonserver.service.apirecord.impl;

import dev.karatay.jsonserver.domain.document.ApiRecord;
import dev.karatay.jsonserver.repository.ApiRecordRepository;
import dev.karatay.jsonserver.service.apirecord.ApiRecordService;
import dev.karatay.jsonserver.util.json.JsonParserUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiRecordServiceImpl implements ApiRecordService {

    private final ApiRecordRepository apiRecordRepository;

    @Override
    public String getRawRecordById(String namespace, String domain, String id) {
        String jsonListString = getRecordRaw(namespace + "/" + domain);
        JSONArray jsonArray = new JSONArray(jsonListString);
        return JsonParserUtil.findByIdInJsonArray(jsonArray, id).toString();
    }

    @Override
    public String getRawRecordList(String namespace, String domain) {
        return apiRecordRepository.findByNamespaceAndDomain(namespace, domain).map(ApiRecord::getJsonData).orElseThrow(()-> new RuntimeException("record not found"));
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
        var index = JsonParserUtil.findIndexByIdInJsonArray(jsonArray, id).orElseThrow(()-> new RuntimeException("record not found!"));
        jsonArray.put(index, new JSONObject(jsonData));
        record.setJsonData(jsonArray.toString());
        return apiRecordRepository.save(record).getJsonData();
    }

    @Override
    public String deleteRecordById(String namespace, String domain, String id) {
        var record = apiRecordRepository.findById(namespace + "/" + domain).orElseThrow(()-> new RuntimeException("record not found!"));
        JSONArray jsonArray = new JSONArray(record.getJsonData());
        var index = JsonParserUtil.findIndexByIdInJsonArray(jsonArray, id).orElseThrow(()-> new RuntimeException("record not found!"));
        jsonArray.remove(index);
        record.setJsonData(jsonArray.toString());
        return apiRecordRepository.save(record).getJsonData();
    }


    public String getRecordRaw(String id) {
        return apiRecordRepository.findById(id).map(ApiRecord::getJsonData).orElseThrow(()-> new RuntimeException("record not found"));
    }

}
