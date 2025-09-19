package com.cristiandiaz.logs_activemq_project.service.log.impl;

import static com.cristiandiaz.logs_activemq_project.utils.LogConstants.OBJECT_TO_STRING_ERROR_LOG;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import com.cristiandiaz.logs_activemq_project.dto.QueueMessageDTO;
import com.cristiandiaz.logs_activemq_project.service.jms.producer.JmsProducerService;
import com.cristiandiaz.logs_activemq_project.service.log.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final JmsProducerService jmsProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public void sendLog(QueueMessageDTO queueMessage) throws Exception {
        String message = convertMessageToString(queueMessage);
        sendMessageToQueue(message);
    }

    private String convertMessageToString(QueueMessageDTO queueMessage) {
        String queueMessageStr = EMPTY;
        try {
            queueMessageStr = objectMapper.writeValueAsString(queueMessage);
        } catch (JsonProcessingException e) {
            log.error(OBJECT_TO_STRING_ERROR_LOG);
        }
        return queueMessageStr;
    }

    private void sendMessageToQueue(String message) {
        Stream.of(message)
                .filter(Strings::isNotBlank)
                .forEach(jmsProducerService::sendMessage);
    }
}
