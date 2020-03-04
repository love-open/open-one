package com.liu.auth.domain;

import lombok.Data;

@Data
public class SysRole {
    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一识别码
     */
    private String uniqueId;

    /**
     * 备注
     */
    private String remark;

}