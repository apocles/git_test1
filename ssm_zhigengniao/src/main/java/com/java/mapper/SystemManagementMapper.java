package com.java.mapper;

import com.java.pojo.OneMenu;
import com.java.pojo.SystemUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/27 16:54
 */
public interface SystemManagementMapper {

    //查询角色
    @Select("SELECT id,roleName,status,DATE_FORMAT(updateTime,'%Y-%m-%d')as updateTime,isRoot  FROM system_roles ")
    List<Map<String,Object>> selectRoles();

    //改变角色的状态
   int updateRoles(@Param("id") Long id);

   //获取可以授权的权限
    @Select("SELECT * FROM system_authoriy WHERE  flag=0 AND parentId=#{id}")
    List<Map<String,Object>> selectAuthority(@Param("id") Long id);

    //查询添加的角色是否存在
    @Select("SELECT count(*) FROM system_roles WHERE roleName=#{roleName}")
    int selectRoleNameExits(String roleName);

    //添加角色,并且返回一条主键值
    @Insert("INSERT INTO system_roles VALUES(null,#{roleName},'1',NOW())")
    @Options(useGeneratedKeys = true,keyProperty ="roleId"  ,keyColumn ="id" )
    int addRoles(Map<String,Object> paramMap);


    //在角色权限表中插入数据
    int addRolesAuthority(@Param("roleId") Long roleId,@Param("authorityAttr") String []authorityAttr );

    //获取系统用户信息
    @Select("SELECT * FROM system_users ")
    List<Map<String,Object>> selectSystemUser();

    //获取普通角色,用于新增用户
    @Select("SELECT * FROM system_roles WHERE isRoot='0'")
    List<Map<String,Object>> selectRoles1();

    //查询账号是否存在
    @Select("SELECT COUNT(*) FROM system_users WHERE account=#{0}")
    int selectAccount(String account);


    //新增系统用户
    @Insert("INSERT INTO system_users VALUES(NULL,NULL,#{account},#{phone},#{email},#{uName},#{password},#{status},#{roleId})")
    int addSystemUser(SystemUser systemUser);

    //查询一级菜单
    List<OneMenu> selectOneMenu();

    //查询角色对应的权限
     @Select("SELECT authorityId FROM system_role_authority WHERE roleId=#{0}")
    List<Long> selectAuthorityByRole(Long roleId);

     //删除去掉的权限对应的角色关系
    @Delete("DELETE FROM system_role_authority WHERE roleId=#{0}")
    int deleteRoleAuthority(Long roleId);



}
