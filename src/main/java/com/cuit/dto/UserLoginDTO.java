package com.cuit.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : UserLoginDTO
 * @packageName : com.cuit.dto
 * @description : 一般类
 * @date : 2020-06-07 16:45
 **/
@Data
public class UserLoginDTO {
    @NotNull(message = "用户名不能为空")
    String username;
    @NotNull(message = "密码不能为空")
    String password;

}
