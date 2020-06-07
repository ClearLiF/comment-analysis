package com.cuit.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Author Jwei
 * @Date 2020/6/2 0:00
 */
@Component
public class Hdfs {
    @Value("${hadoop.hdfs}")
    private String hdfs;
    @Value("${hadoop.out.file}")
    private String outFile;
    private FileSystem fs = null;
    private FSDataOutputStream fsDataOutputStream = null;
    private BufferedWriter writer = null;

    public void init() throws IOException {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", this.hdfs);
        fs = FileSystem.get(conf);
        fsDataOutputStream = fs.create(new Path(outFile), true);
        writer = new BufferedWriter(new OutputStreamWriter(fsDataOutputStream));
    }

    public void close() throws IOException {
        writer.flush();
        fsDataOutputStream.flush();
        fsDataOutputStream.close();
        writer.close();
        fs.close();
    }

    public BufferedWriter getWriter() {
        return writer;
    }
}
