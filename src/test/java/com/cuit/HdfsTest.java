package com.cuit;

import com.cuit.mr.Hdfs;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @Author Jwei
 * @Date 2020/6/2 11:28
 */
public class HdfsTest {

    @Test
    public void write() {
        Hdfs hdfs = new Hdfs();
        try {
            hdfs.init();
            BufferedWriter writer = hdfs.getWriter();
            writer.write("hello");
            writer.newLine();
            writer.write("world");
            writer.newLine();
            writer.flush();
            hdfs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
