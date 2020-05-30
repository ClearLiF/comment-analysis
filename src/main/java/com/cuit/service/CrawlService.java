package com.cuit.service;

import com.cuit.mapper.CommentMapper;
import com.cuit.model.Comment;
import com.cuit.util.JDComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @Author Jwei
 * @Date 2020/5/30 23:12
 */
@Service
public class CrawlService {
    private CommentMapper commentMapper;

    @Transactional
    public boolean getAndSave(int num) {
        String[] keywords = {"手机", "电脑", "男装", "女装"};
        int[] types = {1, 2, 3};
        Random random = new Random();
        String keyword = keywords[random.nextInt(keywords.length)];
        for (int type : types) {
            try {
                List<String> comments = JDComment.getComments(keyword, num, type);
                for (String comment : comments) {
                    Comment c = new Comment();
                    c.setContent(comment);
                    c.setType(type);
                    commentMapper.insertSelective(c);
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
