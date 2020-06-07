package com.cuit.service;

import com.cuit.dto.UserLoginDTO;
import com.cuit.model.Customer;
import com.cuit.model.CustomerMapper;
import com.cuit.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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
@Slf4j
public class CustomerService {
    private CustomerMapper customerMapper;

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public Customer selectByPrimaryKey(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    public boolean login(UserLoginDTO userLoginDTO
            , HttpServletRequest request) {
        Customer login = customerMapper.login(userLoginDTO.getUsername()
                , Objects.requireNonNull(MD5Utils.stringMD5(userLoginDTO.getPassword())).toLowerCase()
        );
        log.info("当前返回值为"+login);
        if (null!=login){
            login.setPassword("");
            request.getSession().setAttribute("nowUser",login);
            return true;
        }
        return false;
    }
}
