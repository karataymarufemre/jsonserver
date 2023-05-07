package dev.karatay.jsonserver.api.response;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String id;
    private Boolean success = true;
    private String errorMessage;
    private T result;

    public BaseResponse(T result) {
        this.result = result;
    }
}
