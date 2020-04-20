package com.example.smail.service;

import com.example.smail.entity.MsgLog;

import java.util.Date;
import java.util.List;

public interface MsgLogService {

    /**
     * 插入消息日志
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 更新消息状态
     * @param msgId
     * @param status
     */
    void updateStatus(String msgId, Integer status);

    /**
     * 查询消息
     * @param msgId
     * @return
     */
    MsgLog selectByMsgId(String msgId);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(String msgId, Date tryTime);
}
