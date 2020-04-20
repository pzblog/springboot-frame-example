package com.example.boot.core.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot.core.error.CommonErrorMsg;
import com.example.boot.core.exception.CommonException;
import com.example.boot.core.result.ResultMsg;
import com.example.boot.core.util.WebUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理
 * @author panzhi
 *
 */
@ControllerAdvice
@Configuration
@Slf4j
public class GlobalExceptionConfig {
	
	private static final Integer GLOBAL_ERROR_CODE = 500;
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		log.error("【统一异常处理器】", e);
		ResultMsg<Object> resultMsg = new ResultMsg<>();
		resultMsg.setCode(GLOBAL_ERROR_CODE);
		if (e instanceof CommonException) {
			CommonException ex = (CommonException) e;
			if(ex.getErrCode() != 0) {
				resultMsg.setCode(ex.getErrCode());
			}
			resultMsg.setMsg(ex.getErrMsg());
		}else {
			resultMsg.setMsg(CommonErrorMsg.SYSTEM_ERROR.getMessage());
		}
		WebUtil.buildPrintWriter(response, resultMsg);
    }
	
	
}
