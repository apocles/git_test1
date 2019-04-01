package com.java.controller.admin;

import com.github.pagehelper.PageInfo;
import com.java.service.AppBaseSetService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/22 23:24
 */
@Controller
public class AppBaseSetController {

    @Autowired
    private AppBaseSetService adviceService;

    @RequestMapping("/getAdvices")
    public @ResponseBody Map<String,Object> getAdvice(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer rows) {
        List<Map<String, Object>> adviceList = adviceService.findAdvice(page, rows);
        PageInfo pageInfo =new PageInfo(adviceList);
        Map<String,Object> resultMap=new HashMap<>();
        //将数据表中的总记录数封装到resultMap中
        resultMap.put("total",pageInfo.getTotal());
        //分页后的数据
        resultMap.put("rows",pageInfo.getList());
        return resultMap;
    }

    //批量改变意见反馈的状态
    @RequestMapping("/getStatus")
    public @ResponseBody boolean getStatus(@RequestParam("idStr") String str){

       return adviceService.modifyStatus(str);
    }



    //批量删除意见反馈
    @RequestMapping("/delete")
    public @ResponseBody boolean getDelete(String str){
        return adviceService.removeAdvice(str);
    }

    //改变单个意见反馈的状态
    @RequestMapping("/oneUpdate")
    public @ResponseBody boolean getOne(Long id){
        return  adviceService.modifyOne(id);
    }

    //查询关于我们
    @RequestMapping("/toAbout")
    public String getAbout(Model model){
       Map<String, Object> aboutMap = adviceService.findAbout();
        model.addAttribute("aboutMap",aboutMap);
        return  "admin/appBaseSet/about.jsp";
    }

    //新增关于我们
    @RequestMapping("/updateAbout")
    public @ResponseBody boolean updateAbout(String content,Long id){
        return  adviceService.modifyAbout(content,id);
    }
}
