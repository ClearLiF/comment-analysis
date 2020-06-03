package com.cuit.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Author Jwei
 * @Date 2020/6/2 0:00
 */
public class HdfsUtil {

    @Value("${hadoop.hdfs}")
    private String hdfs;
    private FileSystem fs = null;

    private void init() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfs);
        fs = FileSystem.get(conf);
        create();
    }

    private void create() throws IOException {
        FSDataOutputStream fsDataOutputStream = fs.create(new Path(""), true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fsDataOutputStream));
        return writer;
    }

}
