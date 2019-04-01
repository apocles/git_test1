package com.java.controller.admin;

import com.github.pagehelper.PageInfo;
import com.java.pojo.OneMenu;
import com.java.pojo.SystemUser;
import com.java.service.SystemManagement;
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
 * @Date: 2019/3/27 17:00
 */

@Controller
public class SystemManagementController {

    @Autowired
    private SystemManagement systemManagement;

    @RequestMapping("/getRoles")
    public @ResponseBody Map<String, Object> getRoles(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer rows){

        List<Map<String, Object>> rolesList = systemManagement.findRoles(page, rows);
        PageInfo pageInfo =new PageInfo(rolesList);
       Map<String,Object> resultMap=new HashMap<String,Object>();
       //将数据表中的总记录数封装到map集合中
       resultMap.put("total",pageInfo.getTotal());
       //将分页后的数据封装到map中
        resultMap.put("rows",pageInfo.getList());

       return resultMap;
    }

    //改变角色的状态
    @RequestMapping("/updateStatus")
    public @ResponseBody boolean getRoles(Long id) {
        return systemManagement.modifyRoles(id);
    }

    //获取普通权限
    @RequestMapping("/getRoleAuthority")
    public @ResponseBody List<Map<String, Object>> getAuthority(@RequestParam(defaultValue = "0") Long id) {
        return systemManagement.findAuthority(id);
    }

    //新增角色
    @RequestMapping("/addRoles")
    public @ResponseBody Map<String,Object> addRoles(String roleName,String authorityId){
        return systemManagement.saveRoles(roleName,authorityId);

    }

    //查询系统用户信息
    @RequestMapping("/getSystemUser")
    public @ResponseBody Map<String,Object> getSystemUser (@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer rows){
        List<Map<String, Object>> systemUser = systemManagement.findSystemUser(page, rows);
        PageInfo pageInfo=new PageInfo(systemUser);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("rows",pageInfo.getList());
        return resultMap;

    }
    //查询角色，用于新增系统用户
    @RequestMapping("/getRoles1")
    public @ResponseBody List<Map<String,Object>> getRoles1 (){
        return systemManagement.findRoles1();
    }

    //新增系统用户
    @RequestMapping("/addSystemUser")
    public @ResponseBody Map<String,Object> addSystemUser(SystemUser systemUser){
        return systemManagement.saveSystemUser(systemUser);
    }

    //查询一级菜单
    @RequestMapping("/getOneMenu")
    public   String getOneMenu(Model model,Long roleId) {
        List<OneMenu> oneMenu = systemManagement.findOneMenu();
        model.addAttribute("oneMenu",oneMenu);
        model.addAttribute("roleId",roleId);
        return "admin/systemManagement/editRoles.jsp";
    }

    @RequestMapping("/getAuthorityId")
    public @ResponseBody List<Long> getAuthorityId(Long roleId){
        return systemManagement.findAuthorityByRole(roleId);
    }

    @RequestMapping("/updateRoleAndAuthority")
    public @ResponseBody boolean updateRoleAndAuthority(Long roleId,String idStr){
        return systemManagement.modifyRoleAuthority(roleId,idStr);
    }
}
