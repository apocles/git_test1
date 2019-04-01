package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.AppBaseSetMapper;
import com.java.service.AppBaseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/22 23:22
 */
@Service
public class AppBaseSetServiceImpl implements AppBaseSetService {


    @Autowired
    private AppBaseSetMapper appBaseSetMapper;

    @Override
    public List<Map<String,Object>> findAdvice(Integer pageNUm,Integer pageSize){
        PageHelper.startPage(pageNUm,pageSize);
        return appBaseSetMapper.selectAdvice();
    }

    @Override
    public boolean modifyStatus(String str) {
        if (str==null|str=="")
            return false;
            str=str.substring(0,str.lastIndexOf(","));
        return appBaseSetMapper.updateStatus(str)>=1;
    }

    @Override
    public boolean modifyOne(Long id) {

        try {
            appBaseSetMapper.updateOne(id);
            return true;
        } catch (Exception e) {
           return  false;
        }
    }


    @Override
    public boolean removeAdvice(String str) {
        if (str==null|str=="")
            return false;

        str=str.substring(0,str.lastIndexOf(","));
        return appBaseSetMapper.deleteAdvice(str)>=1;
    }


    //查询关于我们
    @Override
    public Map<String, Object> findAbout() {
        return appBaseSetMapper.selectAbout();
    }

    //修改
    @Override
    public boolean modifyAbout(String content, Long id) {
        return appBaseSetMapper.UpdateAbout(content,id)==1;
    }


}
