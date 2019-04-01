package com.java.service;

import com.java.pojo.OneMenu;
import com.java.pojo.SystemUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/27 16:59
 */
public interface SystemManagement {

    //查询角色信息
    List<Map<String,Object>> findRoles(Integer pageNum, Integer pageSize);

    //改变角色状态
    boolean modifyRoles( Long id);

    //获取普通权限
    List<Map<String,Object>> findAuthority(Long id);

    //添加角色
   Map<String,Object> saveRoles(String roleName,String authorityId);

   //查询系统用户信息
    List<Map<String,Object>> findSystemUser(Integer pageNum,Integer pageSize);

    //获取普通角色,用于新增用户
    List<Map<String,Object>>findRoles1();

    //新增系统用户
    Map<String,Object> saveSystemUser(SystemUser systemUser);

    //查询一级菜单
    List<OneMenu>findOneMenu();

    //查询角色对应的权限
    List<Long> findAuthorityByRole(Long roleId);


    //修改角色权限
    boolean modifyRoleAuthority(Long roleId,String idStr);

}
