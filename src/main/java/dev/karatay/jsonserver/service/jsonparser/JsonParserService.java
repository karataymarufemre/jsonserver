package dev.karatay.jsonserver.service.jsonparser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

public interface JsonParserService {

    List<JSONObject> findByKeyEquals(JSONArray jsonArray, String key, String value);
    JSONArray findByKeyEqualsArray(JSONArray jsonArray, String key, String value);
    JSONObject findByIdInJsonArray(JSONArray jsonArray, String id);
    Optional<Integer> findIndexByKeyEquals(JSONArray jsonArray, String key, String value);
    Optional<Integer> findIndexByIdInJsonArray(JSONArray jsonArray, String id);
}
