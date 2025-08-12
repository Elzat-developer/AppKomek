package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.EmailMessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailKafkaConsumer {

    private final MailSenderService mailSenderService;

    public EmailKafkaConsumer(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @KafkaListener(topics = "emailMessageTopic", groupId = "email-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(EmailMessageDto emailMessage) {
        mailSenderService.send(
                emailMessage.to(),
                emailMessage.subject(),
                emailMessage.text()
        );
    }
}
