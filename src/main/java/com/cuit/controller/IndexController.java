package com.cuit.controller;

import com.cuit.controller.interceptor.UnAuthRequest;
import com.cuit.dto.UserLoginDTO;
import com.cuit.result.Result;
import com.cuit.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Jwei
 * @Date 2020/5/31 23:41
 */
@Controller
@Slf4j
public class IndexController {

     private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@GetMapping("/")
    public String index() {
        return "public";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("content", "analysis");
        return "index";
    }
    @UnAuthRequest
    @ResponseBody
    @RequestMapping(value = "/loginInto",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public Object loginInto(@Valid UserLoginDTO userLoginDTO
            , BindingResult results,
        HttpServletRequest request){
        if (results.hasErrors()){
            log.error(">>>>登录错误信息:>>>>{}", userLoginDTO);
            return results.getFieldError();
        }
        System.out.println(userLoginDTO);
        if (customerService.login(userLoginDTO,request)){
            log.info(userLoginDTO.toString());
            return new Result<String>(userLoginDTO.getUsername());
        }else {
            log.warn(userLoginDTO.getUsername()+"登录失败");
            return new Result<String>(1000,"登录失败");
        }

    }
    @UnAuthRequest
    @RequestMapping("login")
    public String loginInto(){
        return "login";
    }
}
