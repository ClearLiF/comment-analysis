package com.cuit.controller;

import com.cuit.result.Result;
import com.cuit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private CommentService commentService;

    @ResponseBody
    @GetMapping("get")
    public Result get(int num) {
        commentService.get(num);
        return new Result();
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
