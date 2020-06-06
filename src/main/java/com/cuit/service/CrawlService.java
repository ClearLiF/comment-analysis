package com.cuit.service;

import com.cuit.mapper.CommentMapper;
import com.cuit.model.Comment;
import com.cuit.util.JDComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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

    @Async
    public void getAndSave(int num, String keyword, HttpSession session) {
        session.setAttribute("crawlerRunning", true);
        String[] types = {"正在获取差评", "正在获取中评", "正在获取好评"};
        for (int i = 1; i <= 3; i++) {
            session.setAttribute("currType", types[i - 1]);
            session.setAttribute("typeProgress", i - 1);
            try {
                session.setAttribute("crawlerStatus", "正在爬取数据...该过程耗时较长，请耐心等待。");
                session.setAttribute("crawlNum", 2 * num);
                List<String> comments = JDComment.getComments(keyword, num, i, session);
                session.setAttribute("crawlerStatus", "爬取数据完成,正在将数据写入数据库...");
                for (int j = 0; j < comments.size(); j++) {
                    Comment c = new Comment();
                    c.setContent(comments.get(i));
                    c.setType(i);
                    commentMapper.insertSelective(c);
                    session.setAttribute("currCrawlNum", num + j + 1);
                }
            } catch (IOException e) {
                return;
            }
        }
        session.setAttribute("currType", "已完成");
        session.setAttribute("typeProgress", 3);
        session.setAttribute("crawlerStatus", "爬取任务完成。");
        session.setAttribute("crawlerRunning", false);
    }

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
