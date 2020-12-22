package com.qwplus.quasiworld.common.exception;

import com.qwplus.quasiworld.common.response.ResultCode;

/**
 * @ClassName: CustomException
 * @description 自定义异常处理
 * @Date 2020年12月17日 11:30:21
 */
public class CustomException extends RuntimeException {

    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        super(resultCode.msg());
        this.resultCode = resultCode;
    }

    public CustomException(String msg) {
        super(msg);
        this.resultCode = new ResultCode() {
            @Override
            public boolean success() {
                return false;
            }

            @Override
            public int status() {
                return 600;
            }

            @Override
            public String msg() {
                return msg;
            }
        };
      //  this.resultCode = new CommonCode(false, 600, msg);
    }
    public ResultCode getResultCode(){
        return resultCode;
    }


}
