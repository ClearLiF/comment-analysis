package com.cuit.controller;

import com.cuit.dto.PageBeanDTO;
import com.cuit.mapper.CommentMapper;
import com.cuit.model.Comment;
import com.cuit.result.Result;
import com.cuit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    public CommentService commentService;
    private CommentMapper commentMapper;

    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("insert")
    @ResponseBody
    public Result<String> insert(Comment comment) {
        commentMapper.insertSelective(comment);
        return new Result<>("insert success!");
    }

    /***
     * @date 2020/6/5 13:12
     * @author jwei
     * @return com.cuit.result.Result<java.lang.String>
     */
    @PostMapping("update")
    @ResponseBody
    public Result<String> update(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
        return new Result<>("update success!");
    }

    /**
     * @param id
     * @return com.cuit.result.Result<java.lang.String>
     * @date 2020/6/5 9:50
     * @author jwei
     */
    @GetMapping("/del/{id}")
    @ResponseBody
    public Result<String> del(@PathVariable int id) {
        commentMapper.deleteByPrimaryKey(id);
        return new Result<>("删除成功！");
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
//    @RequestMapping("/{type}/{currentPage}")
    @RequestMapping("get")
    @ResponseBody
//    public PageBeanDTO<Comment> selectPage(
//            @PathVariable("type") int type
//            , @PathVariable("currentPage") int currentPage) {
    public Result<PageBeanDTO<Comment>> selectPage(int currentPage, int limit) {
        PageBeanDTO<Comment> pageBeanDTO = new PageBeanDTO<>();
        pageBeanDTO.setCurrentPage(currentPage);
        pageBeanDTO.setPageSize(limit);
        PageBeanDTO<Comment> commentPageBeanDTO = commentService.getComment(pageBeanDTO);
        return new Result<>(commentPageBeanDTO);
    }

}
