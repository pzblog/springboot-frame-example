package com.example.boot.core.result;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.example.boot.core.error.CommonErrorMsg;
import com.example.boot.core.error.ErrorMsg;


public class BuildResponseMsg {
	
	
	public static Object buildCustomeMsg(Object data){
		return JSON.toJSON(data);
	}
	
	/** 
     * 操作成功,无返回数据
     * @return 
     */  
    public static Object buildSuccessMsgNoData(){
    	ResultMsg<Object> responseMsg  = new ResultMsg<Object>();  
    	responseMsg.setCode(200); 
    	responseMsg.setMsg("");
        responseMsg.setData(null);
        return JSON.toJSON(responseMsg);
    }
    
    /**
     * 操作成功,有返回数据 
     * @param data
     * @return
     */
    public static Object buildSuccessMsgAndData(Object data){  
    	ResultMsg<Object> responseMsg  = new ResultMsg<Object>();  
    	responseMsg.setCode(200); 
    	responseMsg.setMsg("");
    	responseMsg.setData(data);
    	return JSON.toJSON(responseMsg);
    }
    
    /**
     * 操作失败,无返回数据
     * @param errorMsg
     * @return
     */
    public static Object buildFailMsgNoData(ErrorMsg errorMsg){  
    	ResultMsg<Object> responseMsg  = new ResultMsg<Object>();  
    	responseMsg.setCode(errorMsg.getCode()); 
    	responseMsg.setMsg(errorMsg.getMessage());
    	responseMsg.setData(null);
    	return JSON.toJSON(responseMsg);
    }
    
    public static Object buildCommonFailMsg(String msg){
    	if(StringUtils.isNotEmpty(msg)) {
    		CommonErrorMsg.CUSTOM_ERROR.setMessage(msg);
    	}
    	ResultMsg<Object> responseMsg  = new ResultMsg<Object>();  
    	responseMsg.setCode(CommonErrorMsg.CUSTOM_ERROR.getCode()); 
    	responseMsg.setMsg(CommonErrorMsg.CUSTOM_ERROR.getMessage());
    	responseMsg.setData(null);
    	return JSON.toJSON(responseMsg);
    }
    
    /**
     * 操作失败,有返回数据
     * @param errorMsg
     * @param object
     * @return
     */
//    public static String buildFailMsgAndData(ErrorMsg errorMsg, Object object){  
//    	ResultMsg<Object> responseMsg  = new ResultMsg<Object>();  
//    	responseMsg.setCode(errorMsg.getCode()); 
//    	responseMsg.setMsg(errorMsg.getMessage());
//    	responseMsg.setData(object);
//    	return JSON.toJSONString(responseMsg);
//    }

}
