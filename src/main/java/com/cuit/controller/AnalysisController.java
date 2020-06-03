package com.cuit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Jwei
 * @Date 2020/5/28 16:58
 */
@Controller
@RequestMapping("analysis")
public class AnalysisController {

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("content", "analysis");
        return "index";
    }
}
