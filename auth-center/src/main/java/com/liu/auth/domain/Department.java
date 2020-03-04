package com.liu.auth.domain;

import lombok.Data;

@Data
public class Department {
    /**
     * 主键
     */
    private String departmentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型见数据字典
     */
    private String departmentType;
    /**
     * 字典表中的部门名称
     */
    private String departmenttypename;
    /**
     * 成本中心
     */
    private Integer costCenterid;

    /**
     * 上级机构
     */
    private Integer parentId;


    /**
     * 班次
     */
    private Integer orderClassId;
    /**
     * ERP中的内码
     */
    private String ERPId;
}
