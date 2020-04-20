package com.example.demo;

import com.example.demo.model.dto.DeptDto;
import com.example.demo.service.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeptInfoTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void testInsert(){
        for (int i = 0; i < 40; i++) {
            deptService.insertInfo(new DeptDto("name-" +i));
        }
    }

    @Test
    public void testDelete(){
        deptService.deleteAll();
    }

    @Test
    public void testUpdate() throws Exception {
        DeptDto dept = new DeptDto();
        dept.setDeptId(84l);
        dept.setDeptName("hello world!");
        deptService.updateInfo(dept);
    }

    @Test
    public void testUpdate1() throws Exception {
        DeptDto dept = new DeptDto();
        dept.setDeptId(84l);
        dept.setDeptName("hello world!");
        deptService.saveOrUpdate(dept);
    }

}
