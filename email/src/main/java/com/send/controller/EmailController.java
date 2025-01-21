package com.send.controller;

import com.send.dto.EmailRequest;
import com.send.service.IServiceEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/webhook/sendgrid")
    public ResponseEntity<String> handleSendGridWebhook(@RequestBody List<Map<String, Object>> events) {
        for (Map<String, Object> event : events) {
            System.out.println("Evento: " + event + "=>>      ");
            String email = (String) event.get("email");
            String eventType = (String) event.get("event");
            String messageId = (String) event.get("sg_message_id");
            long timestamp = ((Number) event.get("timestamp")).longValue();

            System.out.println("Email: " + email);
            System.out.println("Evento: " + eventType);
            System.out.println("Message ID: " + messageId);
            System.out.println("Timestamp: " + timestamp);
        }
        return ResponseEntity.ok("Evento procesado");
    }

    @GetMapping("/details")
    public ResponseEntity<String> fetchMessageDetails(@RequestParam String messageId) {
        try {
            serviceEmail.fetchMessageDetails(messageId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Detalles del mensaje obtenidos con éxito.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los detalles del mensaje: " + e.getMessage());
        }
    }

}