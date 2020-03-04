package com.liu.common.util;

import lombok.Data;

/**返回对象封装
 * @author Frank.liu
 * @create 2019-06-25 下午 2:03
 **/
@Data
public class ResultJson {
    /**
     * 返回状态码
     */
    private String code;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 返回数据对象
     */
    private Object data;
}
