package com.synthilearn.commonstarter;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GenericResponse<T> {
    int code;
    String message;
    T resultData;

    public static <T> GenericResponse<T> ok() {
        return GenericResponse.<T>builder()
                .code(0)
                .build();
    }

    public static <T> GenericResponse<T> ok(T resultData) {
        return GenericResponse.<T>builder()
                .code(0)
                .resultData(resultData)
                .build();
    }

    public static <T> GenericResponse<T> ok(T resultData, String errorMessage) {
        return GenericResponse.<T>builder()
                .code(0)
                .resultData(resultData)
                .message(errorMessage)
                .build();
    }

    public static <T> GenericResponse<T> error(int errorCode, String errorMessage) {
        return GenericResponse.<T>builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }

    public static <T> GenericResponse<T> validError(T body) {
        return GenericResponse.<T>builder()
                .code(1000)
                .message("Validation failed")
                .resultData(body)
                .build();
    }
}
