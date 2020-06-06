package com.cuit.mapper;

import com.cuit.model.ModelStatus;
import com.cuit.model.ModelStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModelStatusMapper {
    long countByExample(ModelStatusExample example);

    int deleteByExample(ModelStatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ModelStatus record);

    int insertSelective(ModelStatus record);

    List<ModelStatus> selectByExample(ModelStatusExample example);

    ModelStatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ModelStatus record, @Param("example") ModelStatusExample example);

    int updateByExample(@Param("record") ModelStatus record, @Param("example") ModelStatusExample example);

    int updateByPrimaryKeySelective(ModelStatus record);

    int updateByPrimaryKey(ModelStatus record);
}