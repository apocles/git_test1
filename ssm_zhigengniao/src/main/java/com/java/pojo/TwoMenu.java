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
public class TwoMenu implements Serializable {
    private static final long serialVersionUID = -6761491950355406981L;
    private Long twoId;
    private String twoText;
    private Long parentId;
}
