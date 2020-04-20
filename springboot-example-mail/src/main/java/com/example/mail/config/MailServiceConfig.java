package com.example.mail.config;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Component
public class MailServiceConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.account}")
    private String account;

    @Value("${spring.mail.pass}")
    private String pass;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.personal}")
    private String personal;


    public void sendMailInfo(String toEmail, String subject,String content)  {
        try {
            final Properties props = new Properties();
            //服务器
            props.put("mail.smtp.host", host);
            //端口
            props.put("mail.smtp.port", port);
            //协议
            props.setProperty("mail.transport.protocol", protocol);
            //用户名
            props.put("mail.user", account);
            //密码
            props.put("mail.password", pass);
            //使用smtp身份验证
            props.put("mail.smtp.auth", "true");

            //开启安全协议
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.user"),
                            props.getProperty("mail.password"));
                }
            };

            Session session = Session.getDefaultInstance(props, authenticator);
            session.setDebug(true);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from, MimeUtility.encodeText(personal)));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(content, "text/html;charset=UTF-8");

            //保存信息
            mimeMessage.saveChanges();
            //发送消息
            Transport.send(mimeMessage);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }


    public void doSendHtmlEmail(String toEmail, String subject, String sendHtml,String fileName, File attachment) {
        try {
            //设置了附件名过长问题
            System.setProperty("mail.mime.splitlongparameters", "false");
            final Properties props = new Properties();
            //服务器
            props.put("mail.smtp.host", host);
            //端口
            props.put("mail.smtp.port", port);
            //协议
            props.setProperty("mail.transport.protocol", protocol);
            //用户名
            props.put("mail.user", account);
            //密码
            props.put("mail.password", pass);
            //使用smtp身份验证
            props.put("mail.smtp.auth", "true");

            //开启安全协议
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(props.getProperty("mail.user"),
                            props.getProperty("mail.password"));
                }
            };

            Session session = Session.getDefaultInstance(props, authenticator);
            session.setDebug(true);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from, MimeUtility.encodeText(personal)));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
            //邮件主题
            mimeMessage.setSubject(subject);

            //邮件内容
            Multipart multipart = new MimeMultipart();


            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                //MimeUtility.encodeWord可以避免文件名乱码
                FileDataSource fds=new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(fds));
                attachmentBodyPart.setFileName(MimeUtility.encodeText(fds.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            mimeMessage.setContent(multipart);

            //保存信息
            mimeMessage.saveChanges();
            //发送消息
            Transport.send(mimeMessage);
            logger.info("邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
        }
    }
}
