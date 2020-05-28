package com.cuit.result;

public enum ResultEnum {
    SUCCESS(1000, "请求成功！"),
    Fail(2000,"请求失败");
    
    int code;
    String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
