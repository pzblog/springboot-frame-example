package com.example.smail.mapper;

import com.example.smail.entity.MsgLog;

import java.util.List;

public interface MsgLogMapper {

    /**
     * 插入消息日志
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 更新消息状态
     * @param msgLog
     */
    void updateStatus(MsgLog msgLog);

    /**
     * 查询需要重新投递的消息
     * @return
     */
    List<MsgLog> selectTimeoutMsg();

    /**
     * 更新重试此时
     * @param msgLog
     */
    void updateTryCount(MsgLog msgLog);

    /**
     * 查询消息信息
     * @param msgId
     * @return
     */
    MsgLog selectByPrimaryKey(String msgId);
}
