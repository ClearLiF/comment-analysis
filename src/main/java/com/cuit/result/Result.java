package com.cuit.result;

/**
 * @Author Jwei
 * @Date 2020/5/28 21:19
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(ResultEnum resultEnum, T data) {
        this.code = resultEnum.code;
        this.message = resultEnum.message;
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.code;
        this.message = resultEnum.message;
        this.data = null;
    }

    public Result(T data) {
        this.code = ResultEnum.SUCCESS.code;
        this.message = ResultEnum.SUCCESS.message;
        this.data = data;
    }
    public Result(Integer code,T data){
        if (code==1000){
            this.code = ResultEnum.SUCCESS.code;
            this.message = ResultEnum.SUCCESS.message;
        }else {
            this.code = ResultEnum.Fail.code;
            this.message = ResultEnum.Fail.message;
        }
        this.data = data;
    }

    public Result() {
        this.code = ResultEnum.SUCCESS.code;
        this.message = ResultEnum.SUCCESS.message;
        this.data = null;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
