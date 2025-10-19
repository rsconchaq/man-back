package com.javainuse.bootmysqlcrud.service;

public interface EmailService {
    void sendEmail(String paramString1, String paramString2, String paramString3);

    void sendEmailHtml(String paramString1, String paramString2, String paramString3);
}
