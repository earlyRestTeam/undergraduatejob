package com.lyx.undergraduatejob.services;

import java.util.Map;

public interface ISendMail {
    /**
     *
     * @param title 标题
     * @param templatePath  模板路径
     * @param model 放入 template 内需要的 一些 参数
     * @param acceptUser    接收者的 email
     * @return
     */
    boolean sendHtmlMail(String title, String templatePath, Map<String, Object> model, String acceptUser);
}
