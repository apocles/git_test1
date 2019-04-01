package com.java.controller.admin;

import com.java.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/21 23:32
 */
@Controller
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    //获取权限树
    @RequestMapping("/getMenu")
    public @ResponseBody  List<Map<String,Object>> getMenu(@RequestParam(defaultValue = "0") Long id,HttpSession session){
       String account = (String) session.getAttribute("account");
        return authorityService.findById(account,id);
    }


    //登录
    @RequestMapping("/login")
    public String login(String account, String password, HttpSession session){
        boolean flag = authorityService.findUsers(account,password);
        if (flag){
            session.setAttribute("account",account);
            return "redirect:pages/admin/main.jsp";
        }
        return "redirect:pages/admin/login.jsp";

    }
}
