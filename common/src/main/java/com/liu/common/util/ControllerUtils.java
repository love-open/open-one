package com.liu.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank.liu
 * @description 控制层工具类
 * @date 2019/12/28
 */
public class ControllerUtils {

    /**
     *功能描述 ：生成简单的返回信息
     * @author Frank.liu
     * @date 2019/12/28
     * @param isSuccess
     * @return
     */
    public static Map<String, Object> generateResultMsg(Boolean isSuccess){
        Map<String, Object> result = new HashMap<>();
        result.put("isSuccess", isSuccess);
        if(isSuccess){
            result.put("msg", "操作成功");
        }else{
            result.put("msg", "操作失败");
        }
        return result;
    }
}
