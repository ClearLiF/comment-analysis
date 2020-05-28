package com.cuit.controller;

import com.cuit.model.Customer;
import com.cuit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : CustomerController
 * @packageName : com.cuit.controller
 * @description : 一般类
 * @date : 2020-05-28 13:48
 **/
@Controller
@RequestMapping("customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    @ResponseBody
    @RequestMapping(value = "/getCus")
    public Customer getCustomer(Integer id){
        System.out.println("id = " + id);
        return customerService.selectByPrimaryKey(id);
    }

}
