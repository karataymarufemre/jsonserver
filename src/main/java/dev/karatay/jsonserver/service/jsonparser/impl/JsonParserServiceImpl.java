package dev.karatay.jsonserver.service.jsonparser.impl;

import dev.karatay.jsonserver.service.jsonparser.JsonParserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class JsonParserServiceImpl implements JsonParserService {

    @Override
    public List<JSONObject> findByKeyEquals(JSONArray jsonArray, String key, String value) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)))
                .filter(i->{
                    try {
                        return i.get(key).toString().equals(value);
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .toList();
    }

    @Override
    public JSONArray findByKeyEqualsArray(JSONArray jsonArray, String key, String value) {
        var jsonObjectList = findByKeyEquals(jsonArray, key, value);
        if(jsonObjectList.isEmpty()) {
            return new JSONArray("[]");
        }

        return new JSONArray(jsonObjectList);

    }

    @Override
    public JSONObject findByIdInJsonArray(JSONArray jsonArray, String id) {
        return findByKeyEquals(jsonArray, "id", id).stream().findAny().orElseGet(JSONObject::new);
    }

    @Override
    public Optional<Integer> findIndexByKeyEquals(JSONArray jsonArray, String key, String value) {
        for(int i = 0; i< jsonArray.length(); i++) {
            if(((JSONObject)jsonArray.get(i)).get(key).toString().equals(value)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> findIndexByIdInJsonArray(JSONArray jsonArray, String id) {
        return findIndexByKeyEquals(jsonArray, "id", id);
    }
}
