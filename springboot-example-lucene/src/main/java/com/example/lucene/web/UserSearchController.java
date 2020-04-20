package com.example.lucene.web;

import com.example.lucene.model.User;
import com.example.lucene.model.vo.PageQuery;
import com.example.lucene.service.LuceneService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserSearchController {

    @Autowired
    private LuceneService luceneService;


    @PostMapping("/searchUser")
    private PageQuery<User> searchUser(PageQuery<User> pageQuery) throws IOException, ParseException {
        return luceneService.searchUser(pageQuery);
    }

}
