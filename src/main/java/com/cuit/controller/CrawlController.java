package com.cuit.controller;

import com.cuit.result.Result;
import com.cuit.service.CrawlService;
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
@RequestMapping("crawl")
public class CrawlController {

    private CrawlService crawlService;

    @ResponseBody
    @GetMapping("get")
    public Result get(int num) {
        if (crawlService.getAndSave(num)) {
            return new Result("爬取并保存数据成功！");
        } else {
            return new Result("爬取出错！");
        }
    }

    @Autowired
    public void setCrawlService(CrawlService crawlService) {
        this.crawlService = crawlService;
    }
}
