package com.qwplus.quasiworld.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: QWResult
 * @description 返回结果装类
 * @Date 2020年12月17日 11:08:43
 */
@Data
@NoArgsConstructor
public class QWResult<T> extends ResponseResult {

    private T data;

    public QWResult(T data) {
        super();
        this.data = data;
    }

    public QWResult(ResultCode resultCode, T data) {
        super(resultCode);
        this.data = data;
    }

    public static QWResult ok() {
        return new QWResult(CommonCode.SUCCESS, null);
    }

    public static QWResult ok(Object data) {
        return new QWResult(CommonCode.SUCCESS, data);
    }

    public static QWResult build(ResultCode resultCode) {
        return new QWResult(resultCode, null);
    }

}
