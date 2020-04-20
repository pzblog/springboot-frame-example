package com.example.lucene.repository;

import com.example.lucene.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA操作接口
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 自定义JPA
     * 通过用户名查询
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);


    /**
     * 分页查询信息
     * @param pageable
     * @return
     */
    Page<User> findALLByUserNameLike(String userName, Pageable pageable);


    @Transactional(timeout = 10)
    @Query("select u from User u where u.email like ?1")
    List<User> findByEmailAddress(String email);

}
