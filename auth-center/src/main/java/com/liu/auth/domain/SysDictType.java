package com.liu.auth.domain;

import lombok.Data;

@Data
public class SysDictType {
    /**
     * 主键Id
     */
    private Integer id;
    /**
     * 对照码
     */
    private String code;

    /**
     * 对照值
     */
    private String value;

    /**
     * 名称
     */
    private String name;
    /**
     * 父级Id
     */
    private String parentId;

}