package com.cuit.service;

import com.cuit.model.Customer;
import com.cuit.model.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : CustomerService
 * @packageName : com.cuit.service
 * @description : 一般类
 * @date : 2020-05-28 13:46
 **/
@Service
@Transactional
public class CustomerService {
   private CustomerMapper customerMapper ;
    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public Customer selectByPrimaryKey(Integer id){
        return customerMapper.selectByPrimaryKey(id);
    }
}
