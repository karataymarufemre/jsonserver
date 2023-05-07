package dev.karatay.jsonserver.util.json;

import lombok.experimental.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@UtilityClass
public class JsonParserUtil {

    public static List<JSONObject> findByKeyEquals(JSONArray jsonArray, String key, String value) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)))
                .filter(i->i.get(key).toString().equals(value))
                .toList();
    }

    public static JSONObject findByIdInJsonArray(JSONArray jsonArray, String id) {
        return findByKeyEquals(jsonArray, "id", id).stream().findAny().orElseGet(JSONObject::new);
    }

    public static Optional<Integer> findIndexByKeyEquals(JSONArray jsonArray, String key, String value) {
        for(int i = 0; i< jsonArray.length(); i++) {
            if(((JSONObject)jsonArray.get(i)).get(key).toString().equals(value)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public static Optional<Integer> findIndexByIdInJsonArray(JSONArray jsonArray, String id) {
        return findIndexByKeyEquals(jsonArray, "id", id);
    }
}
