package com.clawhub.minibooksearch.exception;

import com.clawhub.minibooksearch.core.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <Description> ExceptionHandle<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018 -09-10 <br>
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


    /**
     * Exception get string.
     *
     * @param e the e
     * @return the string
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exceptionGet(Exception e) {
        logger.info("异常类型：" + e.getClass());
        logger.info("异常信息：" + e);
        //返回系统异常
        return ResultUtil.getError("2000");
    }
}
