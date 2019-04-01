package com.java.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/25 21:33
 */
public interface AppBaseSetMapper {

    //查询意见反馈
    @Select("SELECT sa.id,sa.content,sa.phone,sa.appUserId,DATE_FORMAT(sa.createTime,'%Y-%m-%d')as createTime ,sa.status,au.uName FROM system_advice sa INNER JOIN app_users au ON sa.appUserId=au.id")
    List<Map<String,Object>> selectAdvice();

    //改变单个意见反馈的状态
    int updateOne(@Param("id") Long id);

    //改变意见反馈处理状态
    @Update("UPDATE system_advice SET status=1 WHERE id IN(${str})")
    int updateStatus(@Param("str")String str);

    //批量删除
    @Delete("DELETE FROM system_advice WHERE id IN(${str}) ")
     int deleteAdvice(@Param("str")String str);

    //关于我们
    @Select("SELECT * FROM app_notice WHERE TYPE='1' ORDER BY updateTime DESC LIMIT 1")
    Map<String, Object> selectAbout ();


    //新增关于我们
    @Insert("UPDATE app_notice SET content=#{content},NOW() WHERE id=#{0} ")
    int UpdateAbout(@Param("content") String content,@Param("id") Long id);

}
