package com.cuit.model;

import com.cuit.model.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Customer login(@Param("username") String username,@Param("password") String password);
}