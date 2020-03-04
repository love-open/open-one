package com.liu.auth.exception;

import com.liu.auth.domain.ResultJson;
import lombok.Getter;

/**
 * @author Frank.liu
 * Created at 2018/8/24.
 */
@Getter
public class CustomException extends RuntimeException{
    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }
}
