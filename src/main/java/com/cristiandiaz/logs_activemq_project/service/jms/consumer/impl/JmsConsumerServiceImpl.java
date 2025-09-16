package com.cristiandiaz.logs_activemq_project.service.jms.consumer.impl;

import com.cristiandiaz.logs_activemq_project.dto.QueueMessageDTO;
import com.cristiandiaz.logs_activemq_project.service.jms.consumer.JmsConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JmsConsumerServiceImpl implements JmsConsumerService {

    private final ObjectMapper objectMapper;

    @JmsListener(destination = "${jms.queue.destination}")
    public void listenQueueMessage(String queueMessageStr) {
        QueueMessageDTO queueMessage = QueueMessageDTO.builder().build();
        try {
            queueMessage = objectMapper.readValue(queueMessageStr, QueueMessageDTO.class);
        } catch (JsonProcessingException e) {
            log.error("Error convirtiendo String a objeto");
        }
        log.info("Mensaje recibido: {}", queueMessage);
    }
}
