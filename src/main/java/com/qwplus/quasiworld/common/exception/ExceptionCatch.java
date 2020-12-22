package com.qwplus.quasiworld.common.exception;

import com.qwplus.quasiworld.common.response.CommonCode;
import com.qwplus.quasiworld.common.response.ResponseResult;
import com.qwplus.quasiworld.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ExceptionCatch
 * @description 统一异常捕获
 * @Date 2020年12月11日 11:16:07
 */
@ControllerAdvice(basePackages = "com.qwplus")
@Slf4j
public class ExceptionCatch {

    //自定义异常捕获
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException customException){
        log.error(ExceptionUtils.getStackTrace(customException));
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    //所有未知异常与错误捕获
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception){
        log.error(ExceptionUtils.getStackTrace(exception));
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

}
