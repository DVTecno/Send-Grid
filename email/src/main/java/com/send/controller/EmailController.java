package controller;

import dto.EmailRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.IServiceEmail;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final IServiceEmail serviceEmail;

    public EmailController(IServiceEmail serviceEmail) {
        this.serviceEmail = serviceEmail;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        System.out.println("Email: " + emailRequest.email());
        try {
            serviceEmail.sendEmail(emailRequest.email(), emailRequest.message(), emailRequest.subject());
            return "Email enviado exitosamente.";
        } catch (Exception e) {
            return "Error al enviar el email: " + e.getMessage();
        }
    }
}