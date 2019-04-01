package com.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/22 8:13
 */
public interface AuthorityService {

    List<Map<String,Object>> findById(String account,Long id);

    boolean findUsers(String account,String password);


}
