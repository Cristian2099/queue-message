package com.cristiandiaz.logs_activemq_project.service.jms.consumer.impl;

import static com.cristiandiaz.logs_activemq_project.utils.LogConstants.MESSAGE_RECEIVED_LOG;
import static com.cristiandiaz.logs_activemq_project.utils.LogConstants.STRING_TO_OBJECT_ERROR_LOG;

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

    @Override
    @JmsListener(destination = "${jms.queue.destination}")
    public void listenQueueMessage(String queueMessageStr) {
        QueueMessageDTO convertedMessage = convertStringToMessage(queueMessageStr);
        log.info(MESSAGE_RECEIVED_LOG, convertedMessage);
    }

    private QueueMessageDTO convertStringToMessage(String message) {
        QueueMessageDTO queueMessage = QueueMessageDTO.builder().build();
        try {
            queueMessage = objectMapper.readValue(message, QueueMessageDTO.class);
        } catch (JsonProcessingException e) {
            log.error(STRING_TO_OBJECT_ERROR_LOG);
        }
        return queueMessage;
    }
}
