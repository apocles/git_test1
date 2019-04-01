package com.java.service.impl;

import com.java.mapper.AuthorityMapper;
import com.java.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/22 8:10
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public   List<Map<String,Object>> findById( String account,Long id){
        return authorityMapper.selectById(account,id);
    }

    @Override
    public boolean findUsers(String account,String password) {
    return authorityMapper.selectUsers(account,password)==1;


    }

}
