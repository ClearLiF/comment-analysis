package com.cuit.controller;

import com.cuit.controller.interceptor.UnAuthRequest;
import com.cuit.dto.AnalysisResultDTO;
import com.cuit.mapper.ModelStatusMapper;
import com.cuit.model.ModelStatus;
import com.cuit.result.Result;
import com.cuit.service.impl.AnalysisServiceImpl;
import com.cuit.service.impl.AnalysisServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Jwei
 * @Date 2020/5/28 16:58
 */
@Controller
@RequestMapping("analysis")
public class AnalysisController {

    private ModelStatusMapper modelStatusMapper;
    private AnalysisServiceImpl analysisServiceImpl;
    private AnalysisServiceImpl2 analysisServiceImpl2;
    @UnAuthRequest
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("content", "analysis");
        return "index";
    }

    @UnAuthRequest
    @GetMapping("analysis")
    @ResponseBody
    public Result<AnalysisResultDTO> analysis(String text) {
        ModelStatus modelStatus = modelStatusMapper.selectByPrimaryKey(0);
        AnalysisResultDTO analysis = null;
        if (modelStatus.getStatus() == 1) {
            analysis = analysisServiceImpl.analysis(text);
        } else {
            analysis = analysisServiceImpl2.analysis(text);
        }
        return new Result<>(analysis);
    }

    @Autowired
    public void setAnalysisServiceImpl(AnalysisServiceImpl analysisServiceImpl) {
        this.analysisServiceImpl = analysisServiceImpl;
    }

    @Autowired
    public void setAnalysisServiceImpl2(AnalysisServiceImpl2 analysisServiceImpl2) {
        this.analysisServiceImpl2 = analysisServiceImpl2;
    }

    @Autowired
    public void setModelStatusMapper(ModelStatusMapper modelStatusMapper) {
        this.modelStatusMapper = modelStatusMapper;
    }
}
