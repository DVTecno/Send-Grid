package com.send.controller;

import com.send.dto.EmailRequest;
import com.send.service.IServiceEmail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final IServiceEmail serviceEmail;

    public EmailController(IServiceEmail serviceEmail) {
        this.serviceEmail = serviceEmail;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            serviceEmail.sendEmail(emailRequest.email(), emailRequest.message(), emailRequest.subject());
            return "Email enviado exitosamente.";
        } catch (Exception e) {
            return "Error al enviar el email: " + e.getMessage();
        }
    }
}