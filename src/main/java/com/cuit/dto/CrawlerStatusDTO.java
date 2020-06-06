package com.cuit.dto;

import lombok.Data;

import javax.servlet.http.HttpSession;

/**
 * @Author Jwei
 * @Date 2020/6/5 22:51
 */
@Data
public class CrawlerStatusDTO {
    private Boolean running;
    private String status;
    private String currType;
    private Integer typeProgress;
    private Integer crawlNum;
    private Integer currCrawlNum;

    public CrawlerStatusDTO(HttpSession session) {
        this.running = (Boolean) session.getAttribute("crawlerRunning");
        this.status = (String) session.getAttribute("crawlerStatus");
        this.currType = (String) session.getAttribute("currType");
        this.typeProgress = (Integer) session.getAttribute("typeProgress");
        this.crawlNum = (Integer) session.getAttribute("crawlNum");
        this.currCrawlNum = (Integer) session.getAttribute("currCrawlNum");
    }
}
