package org.example.security.auth.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.security.auth.constant.ApiStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult implements Serializable {

    private static final Map<String, String> map = new HashMap<>(1);
    private int code;
    private String msg;
    private Object data;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public static <T> ApiResult ok() {
        return ok(ApiStatus.OK.getMsg(), null);
    }


    public static <T> ApiResult ok(T data) {
        return ok(ApiStatus.OK.getMsg(), data);
    }


    public static <T> ApiResult ok(String msg, T data) {

        if (msg != null && data instanceof String) {
            String value = (String) data;

            map.clear();
            map.put(msg, value);

            ApiResult apiResult = new ApiResult();
            apiResult.setCode(ApiStatus.OK.getCode());
            apiResult.setMsg(ApiStatus.OK.getMsg());
            apiResult.setData(map);
            apiResult.setTimestamp(LocalDateTime.now());
            return apiResult;
        }

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiStatus.OK.getCode());
        apiResult.setMsg(msg);
        apiResult.setData(data);
        apiResult.setTimestamp(LocalDateTime.now());
        return apiResult;
    }


    public static <T> ApiResult fail(String msg) {
        return fail(msg, null);
    }

    public static <T> ApiResult fail(String msg, T data) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiStatus.FAIL.getCode());
        apiResult.setMsg(msg);
        apiResult.setData(data);
        apiResult.setTimestamp(LocalDateTime.now());
        return apiResult;
    }

    public static <T> ApiResult error(String msg) {
        return error(msg, null);
    }

    public static <T> ApiResult error(String msg, T data) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(ApiStatus.ERROR.getCode());
        apiResult.setMsg(msg);
        apiResult.setData(data);
        apiResult.setTimestamp(LocalDateTime.now());
        return apiResult;
    }

    public static <T> ApiResult instance(ApiStatus apiStatus) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(apiStatus.getCode());
        apiResult.setMsg(apiStatus.getMsg());
        apiResult.setData(null);
        apiResult.setTimestamp(LocalDateTime.now());
        return apiResult;
    }

    public boolean isFail() {
        return this.code != ApiStatus.OK.getCode();
    }

    public boolean isSuccess() {
        return this.code == ApiStatus.OK.getCode();
    }

}
