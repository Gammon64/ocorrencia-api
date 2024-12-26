package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Usuario;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendWelcome(Usuario usuario) {
        kafkaTemplate.send("ocorrencia-topic", usuario.getLogin());
    }
}
