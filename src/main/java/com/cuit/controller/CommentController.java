package com.cuit.controller;

import com.cuit.dto.PageBeanDTO;
import com.cuit.model.Comment;
import com.cuit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : CommentController
 * @packageName : com.cuit.controller
 * @description : 一般类
 * @date : 2020-06-04 21:00
 **/
@Service
@RequestMapping("comment")
public class CommentController {

    public  CommentService commentService;
    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 分页查询
     *
     * @param
     * @return com.cuit.dto.PageBeanDTO<com.cuit.model.Comment>
     * @description
     * @author ClearLi
     * @date 2020/6/4 21:01
     */
    @RequestMapping("/{type}/{currentPage}")
    @ResponseBody
    public PageBeanDTO<Comment> selectPage(
            @PathVariable("type") int type
            , @PathVariable("currentPage") int currentPage) {
        PageBeanDTO<Comment> pageBeanDTO= new PageBeanDTO<Comment>();
        pageBeanDTO.setCurrentPage(currentPage);
        pageBeanDTO.setType(type);
        return commentService.getComment(pageBeanDTO);
    }

}
