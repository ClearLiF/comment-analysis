package com.cuit;

import com.cuit.service.CommentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Jwei
 * @Date 2020/5/30 0:22
 */
public class My {

    @Test
    public void t(){
        CommentService commentService = new CommentService();
        System.out.println("test...");
        commentService.get(10);
    }
}
