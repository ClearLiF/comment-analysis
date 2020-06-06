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
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void run1() {
        double[] temp = new double[3];
        for (double v : temp) {
            System.out.println(v);
        }
    }



}
