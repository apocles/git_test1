package com.java.pojo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/29 22:19
 */

@Data
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1938310538867469869L;
    private String account;
    private String phone;
    private String email;
    private String uName;
    private String password;
    private String status;
    private Integer roleId;


}
