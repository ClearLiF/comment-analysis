package com.cuit.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : JieBaUtil
 * @packageName : com.cuit.util
 * @description : 一般类
 * @date : 2020-06-01 22:22
 **/
public class JieBaUtil {
    private static JiebaSegmenter segmenter = new JiebaSegmenter();

    /**
     * 读取conf目录下所有的自定义词库**.dict文件。
     */
    static {
        WordDictionary.getInstance().init(Paths.get("src/main/resources"));
    }

    public static List<String> testCutForSearch(String sentences) {
        List<String> x = segmenter.sentenceProcess(sentences);
        //移除非中文字符
        x.removeIf(next -> !StringUtil.isAllChinese(next));
//        System.out.println(x);
        return x;
    }
}
