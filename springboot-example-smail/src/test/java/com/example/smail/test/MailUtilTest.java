package com.example.smail.test;

import com.example.smail.SmailApplication;
import com.example.smail.entity.Mail;
import com.example.smail.util.SendMailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmailApplication.class)
public class MailUtilTest {

    @Autowired
    private SendMailUtil sendMailUtil;


    @Test
    public void send() throws Exception {
        Mail mail = new Mail();
        mail.setTo("panzhi@pzblog.cn");
        mail.setTitle("标题");
        mail.setContent("正文");
        sendMailUtil.send(mail);
    }





}
