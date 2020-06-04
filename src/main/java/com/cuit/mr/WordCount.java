package com.cuit.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Jwei
 * @Date 2020/6/3 15:01
 */
@Component
public class WordCount {

    @Value("${hadoop.hdfs}")
    private String hdfs;
    @Value("${hadoop.out.file}")
    private String inFile;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String passwd;

    private static final String[] WCFieldNames = {"word", "type", "count"};

    public int run() throws Exception {

        Configuration conf = new Configuration();
        DBConfiguration.configureDB(conf, driver, url, user, passwd);
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(LongSumReducer.class);
        job.setReducerClass(WordCountReducer.class);

        FileInputFormat.addInputPath(job, new Path(hdfs + inFile));

        DBOutputFormat.setOutput(job, "word_count", WCFieldNames);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(WcWritable.class);
        job.setOutputValueClass(NullWritable.class);

        int ret = job.waitForCompletion(true) ? 0 : 1;
        return ret;
    }

}
