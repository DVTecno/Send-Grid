package com.send.service;

import java.io.IOException;

public interface IServiceEmail {
    void sendEmail(String email, String message, String subject);

    void fetchMessageDetails(String messageId);
}
