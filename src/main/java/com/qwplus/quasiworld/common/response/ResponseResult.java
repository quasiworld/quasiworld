package com.qwplus.quasiworld.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: ResponseResult
 * @description 统一返回结果
 * @Date 2020年12月11日 11:40:18
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult {

    //操作是否成功
    boolean success = true;

    //操作代码
    int status = 200;

    //提示信息
    String msg;



    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.status = resultCode.status();
        this.msg = resultCode.msg();
    }

}
