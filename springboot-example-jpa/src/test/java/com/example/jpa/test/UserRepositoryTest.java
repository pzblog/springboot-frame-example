package com.example.jpa.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jpa.JpaApplication;
import com.example.jpa.model.role.RoleRepository;
import com.example.jpa.model.user.User;
import com.example.jpa.model.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void testUser() throws Exception {
        User user = new User();

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String formattedDate = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(new Date());
            userList.add(new User("A-" + i,"pwd-" + i,"email-" + i,"nick-" + i,formattedDate));
        }

        //删除数据
        userRepository.deleteAll();

        //批量保存信息
        userRepository.saveAll(userList);

        //查询所有的信息
        System.out.println("查询所有的信息：" + JSONArray.toJSON(userRepository.findAll()));

        //通过主键查询信息
        System.out.println("查询单条信息：" + JSONObject.toJSON(userRepository.findById(1L)));

        //获取数据总行数
        System.out.println("总数据：" + userRepository.count());

        //使用自定义JPA查询信息
        System.out.println("自定义查询信息：" + JSONArray.toJSON(userRepository.findByUserName("A-1")));

        //使用自定义JPA，分页查询信息
        Page<User> dataList = userRepository.findAll(PageRequest.of(1, 10, new Sort(Sort.Direction.DESC, "id")));
        System.out.println("分页查询信息：" + JSONArray.toJSON(dataList));


        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(1, 10, sort);
        System.out.println("自定义分页查询信息：" + JSONArray.toJSON(userRepository.findALLByUserNameLike("A",pageable)));


        //使用注解SQL查询
        System.out.println("使用注解SQL查询：" + JSONArray.toJSON(userRepository.findByEmailAddress("email-2")));
    }


    @Test
    public void testRole() throws Exception {

        //查询所有的信息
        System.out.println("查询所有的信息：" + JSONArray.toJSON(roleRepository.findAll()));

    }

}
