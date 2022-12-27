package com.rj.demo.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    public int code;
    public String message;
    public T data;
    public long ts;

    public boolean isSuccess() {
        return code == 200;
    }

    @JsonIgnore
    public boolean isFail() {
        return !isSuccess();
    }

    public static ApiResponse success() {
        return ApiResponse.builder().code(200).message("success").ts(System.currentTimeMillis()).build();
    }

    public static ApiResponse success(Object data) {
        return ApiResponse.builder().code(200).message("success").data(data).ts(System.currentTimeMillis()).build();
    }

    public static ApiResponse fail(int code, String msg) {
        return ApiResponse.builder().code(code).message(msg).ts(System.currentTimeMillis()).build();
    }

    public static ApiResponse fail(String msg) {
        return fail(-1, msg);
    }

    public static ApiResponse fail(Object data) {
        return ApiResponse.builder().code(-1).message("fail").data(data).ts(System.currentTimeMillis()).build();
    }

    public static ApiResponse fail() {
        return fail(-1, "fail");
    }
}
