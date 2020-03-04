package com.liu.auth.domain;

import lombok.Data;

@Data
public class SysMenu implements Comparable<SysMenu>{
    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 上级菜单id
     */
    private String parentMenuId;

    /**
     * 显示排序
     */
    private Integer displayOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图标名称
     */
    private String icon;

    /**
     * 是否叶子节点标志
     */
    private Boolean leafFlag;

	@Override
	public int compareTo(SysMenu sysMenu) { //重写Comparable接口的compareTo方法
		return this.displayOrder - sysMenu.getDisplayOrder();// 根据排序字段升序排列，降序修改相减顺序即可
	}
}