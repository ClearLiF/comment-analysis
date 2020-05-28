package com.cuit.controller;

import com.cuit.result.Result;
import com.cuit.result.ResultEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Jwei
 * @Date 2020/5/28 21:11
 */
@Controller
@RequestMapping("comment")
public class CommentController {

    @ResponseBody
    @GetMapping("get")
    public Result get(int num) {
        return new Result();
    }
}
