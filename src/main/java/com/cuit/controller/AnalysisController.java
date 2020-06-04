package com.cuit.controller;

import com.cuit.dto.AnalysisResultDTO;
import com.cuit.result.Result;
import com.cuit.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Jwei
 * @Date 2020/5/28 16:58
 */
@Controller
@RequestMapping("analysis")
public class AnalysisController {

    private AnalysisService analysisService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("content", "analysis");
        return "index";
    }

    @GetMapping("analysis")
    @ResponseBody
    public Result<AnalysisResultDTO> analysis(String text) {
        return new Result<>(analysisService.analysis(text));
    }

    @Autowired
    public void setAnalysisService(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }
}
