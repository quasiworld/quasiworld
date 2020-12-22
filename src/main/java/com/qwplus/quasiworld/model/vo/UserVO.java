package com.qwplus.quasiworld.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserVO
 * @description
 * @Date 2020年12月18日 13:40:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private Integer userId;

    private String userName;//用户名称

    private String phone;//电话
}
