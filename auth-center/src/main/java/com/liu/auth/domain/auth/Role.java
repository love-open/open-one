package com.liu.auth.domain.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author : Frank.liu
 * createAt: 2020/2/14
 */
@Data
@Builder
public class Role {
    private String id;
    private String name;
    private String uniqueId;
}
