package com.cuit.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取京东评论
 *
 * @Author Jwei
 * @Date 2020/5/30 21:53
 */
public class JDComment {

    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36";

    /**
     * 获取评论数最多的一些（第一页）商品
     *
     * @param keyword 搜索关键词
     * @return 商品id列表
     * @throws IOException
     */
    private static List<String> getItems(String keyword) throws IOException {
        String url = "https://search.jd.com/Search";
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("keyword", keyword);
        dataMap.put("psort", "4");
        Document doc = Jsoup.connect(url)
                .data(dataMap)
                .userAgent(userAgent)
                .get();
        Elements elements = doc.select("div.p-name.p-name-type-2");
        List<String> itemList = new ArrayList<>();
        String pattern = "//item.jd.com/(.*)\\.html";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = null;
        for (Element element : elements) {
            String href = element.selectFirst("a[href]")
                    .attr("href");
            matcher = p.matcher(href);
            if (matcher.find()) {
                itemList.add(matcher.group(1));
            }
        }
        return itemList;
    }

    /**
     * 获取对应的一页评论内容
     *
     * @param productId 商品id
     * @param type      评论类型：1/差评；2/中评；3/好评；
     * @param page      页码
     * @return
     */
    private static List<String> getComment(String productId, int type, int page) throws IOException {
        String url = "https://club.jd.com/comment/productPageComments.action";
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("productId", productId);
        dataMap.put("score", String.valueOf(type));
        dataMap.put("page", String.valueOf(page));
        dataMap.put("sortType", "5");
        dataMap.put("pageSize", "10");
        Document doc = Jsoup.connect(url)
                .data(dataMap)
                .userAgent(userAgent)
                .get();
        List<String> commentList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(doc.body().text());
            JSONArray comments = (JSONArray) jsonObject.get("comments");
            for (Object comment : comments) {
                String content = (String) ((JSONObject) comment).get("content");
                commentList.add(content);
            }
        } catch (JSONException e) {
        }
        return commentList;
    }

    /**
     * 批量获取评论内容
     *
     * @param keyword 查找商品关键字
     * @param num     评论条数
     * @param type    评论类型：1/差评；2/中评；3/好评；
     * @param session
     * @return
     */
    public static List<String> getComments(String keyword, int num, int type, HttpSession session) throws IOException {
        int count = 0;
        session.setAttribute("currCrawlNum", count);
        List<String> items = getItems(keyword);
        List<String> list = new ArrayList<>();
        for (String item : items) {
            int page = 0;
            while (count < num) {
                page++;
                if (page > 20) {
                    break;
                }
                List<String> comments = getComment(item, type, page);
                int size = comments.size();
                list.addAll(comments);
                count += size;
                session.setAttribute("currCrawlNum", count);
                if (size < 10) {
                    break;
                }
            }
            if (count >= num) {
                break;
            }
        }
        return list;
    }
}
