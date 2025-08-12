package app.komek.appkomek.service;

import app.komek.appkomek.model.dto.EmailMessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailKafkaProducer {

    private final KafkaTemplate<Integer, EmailMessageDto> kafkaTemplate;

    public EmailKafkaProducer(KafkaTemplate<Integer, EmailMessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmail(EmailMessageDto messageDto) {
        kafkaTemplate.send("emailMessageTopic", messageDto);
    }
}
