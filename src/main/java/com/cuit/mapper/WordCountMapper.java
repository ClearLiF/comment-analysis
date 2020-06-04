package com.cuit.mapper;

import com.cuit.model.WordCount;
import com.cuit.model.WordCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WordCountMapper {
    long countByExample(WordCountExample example);

    int deleteByExample(WordCountExample example);

    int insert(WordCount record);

    int insertSelective(WordCount record);

    List<WordCount> selectByExample(WordCountExample example);

    int updateByExampleSelective(@Param("record") WordCount record, @Param("example") WordCountExample example);

    int updateByExample(@Param("record") WordCount record, @Param("example") WordCountExample example);
}