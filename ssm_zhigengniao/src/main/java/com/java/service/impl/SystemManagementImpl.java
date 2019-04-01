package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.SystemManagementMapper;
import com.java.pojo.OneMenu;
import com.java.pojo.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/27 16:57
 */
@Service
public class SystemManagementImpl implements com.java.service.SystemManagement {

    @Autowired
    private SystemManagementMapper systemManagementMapper;


    //查询角色列表
    @Override
    public List<Map<String,Object>> findRoles(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return systemManagementMapper.selectRoles();
    }

    //改变角色的状态
    @Override
    public boolean modifyRoles(Long id) {
        return systemManagementMapper.updateRoles(id)==1;
    }

    @Override
    public List<Map<String, Object>> findAuthority(Long id) {
        return systemManagementMapper.selectAuthority(id);
    }

    @Override
    public Map<String,Object> saveRoles(String roleName, String authorityId) {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status",0);
        //1、校验数据:0成功、1失败、2用户名重复、3数据格式错误 -1未知错误
        if (roleName==null ||authorityId==null){
            resultMap.put("status","3");
            return resultMap;
        }
        if (!roleName.matches("[\\u4e00-\\u9fa5]{1,10}")||!authorityId.matches("(\\d,)+")){
            resultMap.put("status","3");
            return  resultMap;
        }
        //2、判断角色名是否存在
        int flag=systemManagementMapper.selectRoleNameExits(roleName);
        if (flag==1){
            resultMap.put("status","2");
            return resultMap;
        }

        //添加角色
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleName",roleName);
          int flag1=systemManagementMapper.addRoles(paramMap);
          if (flag1==0){
              resultMap.put("status","1");
              return resultMap;
          }

          //添加角色与权限之间的关联关系
           Long roleId= (Long) paramMap.get("roleId");
            String[] authorityAttr = authorityId.split("\\,");
            int flag2 = systemManagementMapper.addRolesAuthority(roleId, authorityAttr);
            if (flag2==0){
                resultMap.put("status","-1");
                return resultMap;
            }
            return resultMap;

    }

    //查询系统用户信息
    @Override
    public List<Map<String, Object>> findSystemUser(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return systemManagementMapper.selectSystemUser();
    }

    @Override
    public List<Map<String, Object>> findRoles1() {
        return systemManagementMapper.selectRoles1();
    }

    //新增系统用户
    @Override
    public Map<String,Object> saveSystemUser(SystemUser systemUser) {
        Map resultMap=new HashMap();
        //0成功、1失败、2用户名重复

        resultMap.put("status","0");//添加成功

        //判断用户名是否存在
        int flag = systemManagementMapper.selectAccount(systemUser.getAccount());
        if (flag==1){//用户名重复
            resultMap.put("status","2");
            return  resultMap;
        }
        int flag1 = systemManagementMapper.addSystemUser(systemUser);
        if (flag1==1){//添加成功
          return resultMap;
        }
        resultMap.put("status","1");

        return resultMap;

    }

    //查询一级菜单
    @Override
    public  List<OneMenu> findOneMenu() {
        return systemManagementMapper.selectOneMenu();
    }


    //查询角色对应的权限
    @Override
    public List<Long> findAuthorityByRole(Long roleId) {
        return systemManagementMapper.selectAuthorityByRole(roleId);
    }

    //修改角色权限对应关系
    @Override
    public boolean modifyRoleAuthority(Long roleId, String idStr) {
        //判断是否为空
        if (roleId==null || idStr==null){
            return  false;
        }
        //删除对应角色权限表中的记录
        int i = systemManagementMapper.deleteRoleAuthority(roleId);
        if (i>=1){
            //删除成功,取出重复的id
            String[] idstr = idStr.split(",");
            Set<String> temSet=new HashSet();
            for (String id:idstr){
                temSet.add(id);
            }
          idstr=  temSet.toArray(new String[0]);
            int i1 = systemManagementMapper.addRolesAuthority(roleId, idstr);
            if (i1>=1)
                return true;

        }
        return false;
    }


}
