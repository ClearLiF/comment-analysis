package com.cuit.service;

import com.cuit.mapper.CommentMapper;
import com.cuit.mapper.ModelStatusMapper;
import com.cuit.mapper.WcMapper;
import com.cuit.model.Comment;
import com.cuit.model.CommentExample;
import com.cuit.model.ModelStatus;
import com.cuit.mr.Hdfs;
import com.cuit.mr.WordCount;
import com.cuit.util.JieBaUtil;
import com.cuit.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jwei
 * @Date 2020/6/3 17:50
 */
@Service
public class DisposeService {
    private WcMapper wcMapper;
    private WordCount wordCount;
    private ModelStatusMapper modelStatusMapper;
    private CommentMapper commentMapper;


    private Hdfs hdfs;

    public boolean canRun() {
        ModelStatus modelStatus = modelStatusMapper.selectByPrimaryKey(0);
        if (modelStatus.getStatus() == 1) {
            backup();
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return boolean
     * @date 2020/6/3 14:05
     * @author jwei
     */
    public void commentToHdfs() {
        List<String> list = cutWordsFromDB();
        try {
            hdfs.init();
            BufferedWriter writer = hdfs.getWriter();
            for (String s : list) {
                writer.write(s);
                writer.newLine();
            }
            hdfs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return java.util.List<java.lang.String>
     * @date 2020/6/3 14:40
     * @author jwei
     */
    private List<String> cutWordsFromDB() {
        List<String> list = new ArrayList<>();
        List<Comment> comments = commentMapper.selectByExample(new CommentExample());
        for (Comment comment : comments) {
            List<String> words = JieBaUtil.testCutForSearch(comment.getContent());
            list.add(comment.getType() + "\t" + ListUtil.listToString(words));
        }
        return list;
    }

    @Async
    public void wordCount() {
        ModelStatus modelStatus = modelStatusMapper.selectByPrimaryKey(0);
        modelStatus.setStatus(0);
        modelStatusMapper.updateByPrimaryKey(modelStatus);
        wcMapper.truncate0();
        commentToHdfs();
        try {
            wordCount.run();
            modelStatus.setStatus(1);
            modelStatusMapper.updateByPrimaryKey(modelStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void backup() {
        //备份
        ModelStatus modelStatus1 = modelStatusMapper.selectByPrimaryKey(1);
        modelStatus1.setStatus(0);
        modelStatusMapper.updateByPrimaryKey(modelStatus1);
        wcMapper.truncate1();
        wcMapper.backup();
        modelStatus1.setStatus(1);
        modelStatusMapper.updateByPrimaryKey(modelStatus1);
    }

    @Autowired
    public void setModelStatusMapper(ModelStatusMapper modelStatusMapper) {
        this.modelStatusMapper = modelStatusMapper;
    }

    @Autowired
    public void setWcMapper(WcMapper wcMapper) {
        this.wcMapper = wcMapper;
    }

    @Autowired
    public void setWordCount(WordCount wordCount) {
        this.wordCount = wordCount;
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    public void setHdfs(Hdfs hdfs) {
        this.hdfs = hdfs;
    }
}
