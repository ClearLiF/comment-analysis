package com.cuit.util;

import com.cuit.model.Comment;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : JSONUtil
 * @packageName : com.cuit.util
 * @description : 一般类
 * @date : 2020-05-29 16:24
 **/
public class JSONUtil {
    /**
     * 无建议(默认)
     * @description
     *          将字符串中的分词提取出来 返回词的数组 例如[百度，是，一个 ，高科技，公司]
     * @author ClearLi
     * @date 2020/5/29 16:43
     * @param json 传入的json字符串
     * @return java.util.List<java.lang.String>
     */
    public static List<String>  getWords(String json){
        List<String > list =  new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
       // String items = jsonObject.getString("items");
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < jsonArray.length(); i++) {
                // System.out.println(jsonArray.getJSONObject(i).getString("item"));
                list.add(jsonArray.getJSONObject(i).getString("item"));
            }
        }catch (Exception e){
            System.out.println(json);
        }


        return list;

    }
    /**
     * 无建议(默认)
     * @description
     *   将评论中的分词提取出来 返回词的数组 例如[百度，是，一个 ，高科技，公司]
     *
     * @author ClearLi
     * @date 2020/5/29 17:22
     * @param comment
     *          评论
     *
     * @return java.lang.String
     */
    public static String getWords(Comment comment,String str){
        StringBuilder stringBuffer = new StringBuilder();
        if (comment.getContent() != null) {
            //格式： "好评   "
            List<String> words = getWords(str);
            stringBuffer.append(comment.getType()+"\t");
            for (String word : words) {
                //格式： "好评   词语  词语"
                stringBuffer.append(word).append(" ");
            }
           return stringBuffer.toString();
        }
        return "";
    }

}
