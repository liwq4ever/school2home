package com.simpletech.school2home.utils.result;


public class ResultUtil {
    //当正确时返回的值
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(1);
        result.setMessage("OK");
        result.setObj(data);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(String message) {
        Result result = new Result();
        result.setCode(1);
        result.setMessage(message);
        result.setObj(null);
        return result;
    }

    //当错误时返回的值
    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage("错误：" + msg);
        return result;
    }
}
