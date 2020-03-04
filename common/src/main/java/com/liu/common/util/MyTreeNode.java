package com.liu.common.util;

import lombok.Data;

/** 节点类
 * @author Frank.liu
 * @create 2018-12-03 上午 10:39
 **/
@Data
public class MyTreeNode {
    public String id;
    public String text;
    public String parent;
    public String icon;
}
