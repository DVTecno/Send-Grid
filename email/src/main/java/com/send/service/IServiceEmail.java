package com.send.service;

public interface IServiceEmail {
    void sendEmail(String email, String message, String subject);

    void fetchMessageDetails(String messageId);
}
