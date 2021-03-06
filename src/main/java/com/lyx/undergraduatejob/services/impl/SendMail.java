package com.lyx.undergraduatejob.services.impl;





import com.lyx.undergraduatejob.services.ISendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Map;


@Service
public class SendMail implements ISendMail {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    TemplateEngine templateEngine;
    @Value("${want.mail.senderName}")
    private String name;
    @Override
    public boolean sendHtmlMail(String title, String templatePath, Map<String, Object> model, String acceptUser) {
        try{
            String sendName = MimeUtility.encodeText(name);
            InternetAddress from = new InternetAddress(sendName+"<18216308307@163.com>");
            //创建邮件正文
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            Context context = new Context();
            context.setVariables(model);

            System.out.println("templatePath = " + templatePath);
            String result = templateEngine.process(templatePath, context);

            mimeMessageHelper.setTo(acceptUser);//设置接收者邮箱
            mimeMessageHelper.setFrom(from);//设置发送者邮箱
            mimeMessageHelper.setCc(from);//设置抄送人，抄送给发件人
            mimeMessageHelper.setSubject(title);//设置邮件标题
            mimeMessageHelper.setText(result);//设置邮件内容

            mailSender.send(mimeMessage);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
