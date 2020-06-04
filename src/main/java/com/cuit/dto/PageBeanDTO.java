package com.cuit.dto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author: 李琪
 * @date: 2020/3/17
 * @description 一般类
 */
@Data
public class PageBeanDTO<T> {
    /*
    * 当前页
    * */
    private Integer currentPage;
    /*
    * 总页数
    * */
    private Integer totalPages;
    /*
    * 每页的数量
    * */
    private Integer pageSize=7;
    /*
    * 数据类型
    * */
    private List<T> rows;
    private Integer type=-1;


}
