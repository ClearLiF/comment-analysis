package com.cuit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WcMapper {
    @Update("truncate table word_count;")
    void truncate0();

    @Update("truncate table word_count_bk;")
    void truncate1();

    @Insert("insert into word_count_bk select * from word_count;")
    void backup();


}
