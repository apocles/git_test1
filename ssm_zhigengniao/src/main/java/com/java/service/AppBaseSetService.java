package com.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/22 23:24
 */
public interface AppBaseSetService {
    List<Map<String,Object>> findAdvice(Integer pageNUm,Integer pageSize);
    boolean modifyStatus(@Param("str")String str);

    boolean modifyOne( Long id);

    boolean removeAdvice(@Param("str")String str);

    Map<String, Object> findAbout ();

    boolean modifyAbout( String content,Long id);
}
