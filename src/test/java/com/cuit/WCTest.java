package com.cuit;

import com.cuit.mr.WordCount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Jwei
 * @Date 2020/6/3 17:45
 */
public class WCTest {

    @Autowired
    private WordCount wordCount;

    @Test
    public void run() {
        try {
            System.out.println(wordCount.run());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
