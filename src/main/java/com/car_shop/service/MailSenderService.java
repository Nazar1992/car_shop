package com.car_shop.service;

public interface MailSenderService
{
    void sendMail(String theme, String mailBody, String email);
}
