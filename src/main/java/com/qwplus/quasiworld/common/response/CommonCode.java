package com.qwplus.quasiworld.common.response;

import lombok.ToString;

/**
 * @ClassName: CommonCode
 * @description 自定义返回
 * @Date 2020年12月17日 10:48:30
 */
@ToString
public enum CommonCode implements ResultCode {

    SUCCESS(true, 200, "操作成功！"),
    FAIL(false, 400, "操作失败！"),
    SERVER_ERROR(false, 999999, "抱歉，系统繁忙，请稍后重试！"),
    UNAUTHENTICATED(false, 403, "此操作需要登陆系统！");

    //操作是否成功
    boolean success;
    //操作代码
    int status;
    //提示信息
    String msg;

     CommonCode(boolean success, int status, String msg) {
        this.success = success;
        this.status = status;
        this.msg = msg;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public String msg() {
        return msg;
    }
}
