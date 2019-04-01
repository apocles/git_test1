package com.java.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @discription:
 * @Author: admin
 * @Date: 2019/3/30 13:58
 */

@Data
public class OneMenu implements Serializable {
    private static final long serialVersionUID = -128890517779213847L;
    private Long oneId;
    private String oneText;
    private List<TwoMenu> twoMenuList;
}
