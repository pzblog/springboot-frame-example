package com.example.lucene.service.impl;

import com.example.lucene.model.User;
import com.example.lucene.repository.UserRepository;
import com.example.lucene.service.LuceneService;
import com.example.lucene.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LuceneService luceneService;

    @Override
    public void synUserCreatIndex() throws IOException {
        List<User> userList = userRepository.findAll();
        luceneService.synUserCreatIndex(userList);
    }
}
