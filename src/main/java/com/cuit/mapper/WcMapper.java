package com.cuit.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WcMapper {
    @Update("truncate table word_count;")
    void truncate();
}
