package com.cuit.controller;

import com.cuit.result.Result;
import com.cuit.service.CommentService;
import com.cuit.service.DisposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Jwei
 * @Date 2020/6/1 20:11
 */
@Controller
@EnableAsync
@RequestMapping("dispose")
public class DisposeController {
    private DisposeService disposeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("content", "dispose");
        return "index";
    }


    @PostMapping("wordCount")
    @ResponseBody
    public Result<String> wordCount() {
        if (disposeService.canRun()) {
            disposeService.wordCount();
            return new Result<>("词频统计任务已提交！");
        } else {
            return new Result<>("系统当前正在处理该任务！该任务耗时较长，请耐心等待！！！");
        }
    }


    @Autowired
    public void setDisposeService(DisposeService disposeService) {
        this.disposeService = disposeService;
    }
}
