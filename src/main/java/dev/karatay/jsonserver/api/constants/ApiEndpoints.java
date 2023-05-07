package dev.karatay.jsonserver.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiEndpoints {

    public static final String API_BASE = "/api/v1";

    @UtilityClass
    public class ApiRecord {
        public static final String API = API_BASE + "/record";
    }
}
