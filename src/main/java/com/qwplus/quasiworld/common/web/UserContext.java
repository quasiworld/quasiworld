package com.qwplus.quasiworld.common.web;

import lombok.Data;

/**
 * @ClassName: UserContext
 * @description
 * @Date 2020年12月17日 16:14:47
 */
@Data
public class UserContext {

    private Integer userId;//用户ID

    private String userName;//用户名称

    private String token;//token
}
