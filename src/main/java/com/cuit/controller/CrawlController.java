package com.cuit.controller;

import com.cuit.dto.CrawlerStatusDTO;
import com.cuit.result.Result;
import com.cuit.service.CrawlService;
import com.cuit.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Jwei
 * @Date 2020/5/28 21:11
 */
@Controller
@EnableAsync
@RequestMapping("crawler")
public class CrawlController {

    private CrawlService crawlService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("content", "crawl");
        return "index";
    }

    @ResponseBody
    @GetMapping("get")
    public Result<String> get(int num, String keyword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        HttpUtil.trustAllHosts();
        session.setAttribute("crawlerStatus", "等待中...");
        crawlService.getAndSave(num, keyword, session);
        return new Result("发送任务请求成功！");
    }

    @ResponseBody
    @GetMapping("status")
    public Result<CrawlerStatusDTO> status(HttpServletRequest request) {
        return new Result<>(new CrawlerStatusDTO(request.getSession()));
    }

    @Autowired
    public void setCrawlService(CrawlService crawlService) {
        this.crawlService = crawlService;
    }
}
