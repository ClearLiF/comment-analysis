package com.cuit;

import com.baidu.aip.nlp.AipNlp;
import com.cuit.model.Comment;
import com.cuit.service.CommentService;
import com.cuit.util.JSONUtil;
import org.json.JSONObject;
import org.junit.Test;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : Demo01
 * @packageName : com.cuit
 * @description : 一般类
 * @date : 2020-05-29 13:41
 **/
public class Demo01 {
    @Test
    public void test1() {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        //client.setConnectionTimeoutInMillis(2000);
        //client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
       // client.setHttpProxy("https://aip.baidubce.com/rpc/2.0/nlp/v2/word_emb_vec", 8888);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 调用接口
        String text = "百度是一家高科技公司";
        JSONObject res = client.lexer(text, null);
        System.out.println(res.toString(2));

    }
        //设置APPID/AK/SK
        public static final String APP_ID = "19934789";
        public static final String API_KEY = "GC4wtv9uKrFHYn3SLqbNtsAF";
        public static final String SECRET_KEY = "QwiGRDL6FlC4tayCsmqlxDcWeeC0eGdE";

        @Test
        public void test2(){
            String s  = "{\n" +
                    "  \"log_id\": 3474074057182926429,\n" +
                    "  \"text\": \"百度是一家高科技公司\",\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"formal\": \"\",\n" +
                    "      \"loc_details\": [],\n" +
                    "      \"item\": \"百度\",\n" +
                    "      \"pos\": \"\",\n" +
                    "      \"ne\": \"ORG\",\n" +
                    "      \"basic_words\": [\"百度\"],\n" +
                    "      \"byte_length\": 4,\n" +
                    "      \"byte_offset\": 0,\n" +
                    "      \"uri\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"formal\": \"\",\n" +
                    "      \"loc_details\": [],\n" +
                    "      \"item\": \"是\",\n" +
                    "      \"pos\": \"v\",\n" +
                    "      \"ne\": \"\",\n" +
                    "      \"basic_words\": [\"是\"],\n" +
                    "      \"byte_length\": 2,\n" +
                    "      \"byte_offset\": 4,\n" +
                    "      \"uri\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"formal\": \"\",\n" +
                    "      \"loc_details\": [],\n" +
                    "      \"item\": \"一家\",\n" +
                    "      \"pos\": \"m\",\n" +
                    "      \"ne\": \"\",\n" +
                    "      \"basic_words\": [\n" +
                    "        \"一\",\n" +
                    "        \"家\"\n" +
                    "      ],\n" +
                    "      \"byte_length\": 4,\n" +
                    "      \"byte_offset\": 6,\n" +
                    "      \"uri\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"formal\": \"\",\n" +
                    "      \"loc_details\": [],\n" +
                    "      \"item\": \"高科技\",\n" +
                    "      \"pos\": \"n\",\n" +
                    "      \"ne\": \"\",\n" +
                    "      \"basic_words\": [\n" +
                    "        \"高\",\n" +
                    "        \"科技\"\n" +
                    "      ],\n" +
                    "      \"byte_length\": 6,\n" +
                    "      \"byte_offset\": 10,\n" +
                    "      \"uri\": \"\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"formal\": \"\",\n" +
                    "      \"loc_details\": [],\n" +
                    "      \"item\": \"公司\",\n" +
                    "      \"pos\": \"n\",\n" +
                    "      \"ne\": \"\",\n" +
                    "      \"basic_words\": [\"公司\"],\n" +
                    "      \"byte_length\": 4,\n" +
                    "      \"byte_offset\": 16,\n" +
                    "      \"uri\": \"\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}\n" +
                    "\n" +
                    "Process finished with exit code 0\n";
            JSONUtil.getWords(s);

        }
        @Test
        public void test3(){
            CommentService commentService = new CommentService();
            Comment comment = new Comment();
            comment.setId(1);
            comment.setContent("百度是一家高科技公司");
            comment.setType(1);
            System.out.println(commentService.getPartingWord(comment));
        }
}
