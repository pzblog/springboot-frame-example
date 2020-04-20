package com.example.boot.core.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.example.boot.core.error.CommonErrorMsg;
import com.example.boot.core.result.ResultMsg;

/**
 * 统一报文
 * 结果响应转换
 * @author panzhi
 *
 */
public class ResResultConverter extends MappingJackson2HttpMessageConverter{

	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if(!(object instanceof ResultMsg)) {
			ResultMsg<Object> resultMsg = new ResultMsg<>(CommonErrorMsg.SUCCESS_CODE.getCode(),CommonErrorMsg.SUCCESS_CODE.getMessage());
			resultMsg.setData(object);
			object = resultMsg;
		}
		super.writeInternal(object, type, outputMessage);
	}
	
}
