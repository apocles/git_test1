package com.java.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/22 8:06
 */
public interface AuthorityMapper {

    //根据不同用户来查询菜单栏
    @Select("SELECT sa.* FROM system_roles sr INNER JOIN system_role_authority sra ON sr.id=sra.roleId \n" +
            "INNER JOIN system_authoriy sa ON sa.id=sra.authorityId INNER JOIN system_users su ON \n" +
            "su.roleId= sr.id WHERE su.account=#{0} AND sa.parentId=#{1}")
    List<Map<String, Object>> selectById(String account,Long id);

    //查询后台登录用户
    @Select("SELECT count(*) FROM system_users WHERE account=#{0} AND password=#{1} AND status='1'")
    int selectUsers(String account,String password);




}