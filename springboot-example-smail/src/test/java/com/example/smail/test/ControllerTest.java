package com.example.smail.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void testController() throws Exception {
        for (int i = 0; i < 10; i++) {
            testRestTemplate.postForObject("/test/send?to={to}&title={title}&content={content}", null, String.class, "panzhi@pzblog.cn", "标题", "正文");
        }
    }
}
