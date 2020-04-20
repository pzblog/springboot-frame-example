package com.example.lucene.service;

import com.example.lucene.model.User;
import com.example.lucene.model.vo.PageQuery;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public interface LuceneService {

    /**
     * 将数据同步到lucene
     * @param userList
     * @throws IOException
     */
    void synUserCreatIndex(List<User> userList) throws IOException;


    /**
     * 从lucene中查询数据
     * @param pageQuery
     * @return
     */
    PageQuery<User> searchUser(PageQuery<User> pageQuery) throws IOException, ParseException;


    /**
     * 从lucene中删除索引
     * @param id
     */
    void deleteUserIndexById(String id) throws IOException;
}
