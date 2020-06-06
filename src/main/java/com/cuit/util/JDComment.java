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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取京东评论
 *
 * @Author Jwei
 * @Date 2020/5/30 21:53
 */
public class JDComment {

    private static String[] userAgent = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1",
            "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
            "Mozilla/5.0 (Macintosh; U; Mac OS X Mach-O; en-US; rv:2.0a) Gecko/20040614 Firefox/3.0.0 ",
            "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10.5; en-US; rv:1.9.0.3) Gecko/2008092414 Firefox/3.0.3",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.1) Gecko/20090624 Firefox/3.5",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en-US; rv:1.9.2.14) Gecko/20110218 AlexaToolbar/alxf-2.0 Firefox/3.6.14",
            "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10.5; en-US; rv:1.9.2.15) Gecko/20110303 Firefox/3.6.15",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
            "Opera/9.80 (Android 2.3.4; Linux; Opera mobi/adr-1107051709; U; zh-cn) Presto/2.8.149 Version/11.10",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/531.21.8 (KHTML, like Gecko) Version/4.0.4 Safari/531.21.10",
            "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.2 Safari/533.18.5",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)"
    };
    private static Random random = new Random();

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
                .userAgent(userAgent[0])
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
                .userAgent(userAgent[0])
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
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
