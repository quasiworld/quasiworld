package com.qwplus.quasiworld.common.exception;

import com.qwplus.quasiworld.common.response.ResultCode;

/**
 * @ClassName: ExceptionCast
 * @description 异常抛出
 * @Date 2020年12月17日 15:49:02
 */
public class ExceptionCast {

    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }

    public static void cast(String msg) {
        throw new CustomException(msg);
    }

}
