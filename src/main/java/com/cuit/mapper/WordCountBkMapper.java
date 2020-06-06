package com.cuit.mapper;

import com.cuit.model.WordCountBk;
import com.cuit.model.WordCountBkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WordCountBkMapper {
    long countByExample(WordCountBkExample example);

    int deleteByExample(WordCountBkExample example);

    int insert(WordCountBk record);

    int insertSelective(WordCountBk record);

    List<WordCountBk> selectByExample(WordCountBkExample example);

    int updateByExampleSelective(@Param("record") WordCountBk record, @Param("example") WordCountBkExample example);

    int updateByExample(@Param("record") WordCountBk record, @Param("example") WordCountBkExample example);
}