package com.example.mybatis.mapper;

import com.example.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapperAnnot {


    /**
     * 查询所有的信息
     * @return
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "regTime", column = "reg_time"),
            @Result(property = "userName", column = "user_name")
    })
    List<UserEntity> findAll();


    /**
     * 查询用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "regTime", column = "reg_time"),
            @Result(property = "userName", column = "user_name")
    })
    UserEntity getInfoById(Long id);


    /**
     * 新增数据
     * @param user
     */
    @Insert("INSERT INTO user(id,email,nick_name,pass_word,reg_time,user_name) VALUES(#{id},#{email},#{nickName},#{passWord},#{regTime},#{userName})")
    void insert(UserEntity user);

    /**
     * 修改数据
     * @param user
     */
    @Update("UPDATE user SET nick_name=#{nickName},user_name=#{userName} WHERE id =#{id}")
    void update(UserEntity user);

    /**
     * 删除数据
     * @param id
     */
    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

}
