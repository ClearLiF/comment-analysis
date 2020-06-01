package com.cuit;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import junit.framework.TestCase;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : Demo02
 * @packageName : com.cuit
 * @description : 一般类
 * @date : 2020-06-01 22:16
 **/
public class Demo02 extends TestCase {

        private JiebaSegmenter segmenter = new JiebaSegmenter();
        String sentences = "北京京天威科技发展有限公司大庆车务段的装车数量";

        /**
         * 读取conf目录下所有的自定义词库**.dict文件。
         */
        @Override
        protected void setUp() throws Exception {
            WordDictionary.getInstance().init(Paths.get("resources"));
        }

        @Test
        public void testCutForSearch() {
                List<String> x = segmenter.sentenceProcess(sentences);
                System.out.println(x);


        }
}
