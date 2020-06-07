package com.cuit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Jwei
 * @Date 2020/5/31 23:41
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "public";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("content", "analysis");
        return "index";
    }
}
