package com.qwplus.quasiworld.common.response;

/**
 * @ClassName: ResultCode
 * @description
 * @Date 2020年12月11日 11:35:51
 */

public interface ResultCode {

    boolean success();

    int status();

    String msg();
}
