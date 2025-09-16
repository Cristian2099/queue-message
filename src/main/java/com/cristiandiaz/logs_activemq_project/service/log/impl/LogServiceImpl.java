package com.cristiandiaz.logs_activemq_project.service.log.impl;

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
    public void sendLog(QueueMessageDTO queueMessage) {
        String queueMessageStr = Strings.EMPTY;
        try {
            queueMessageStr = objectMapper.writeValueAsString(queueMessage);
        } catch (JsonProcessingException e) {
            log.error("Error convirtiendo objeto a String");
        }
        Stream.of(queueMessageStr)
                .filter(Strings::isNotBlank)
                .forEach(jmsProducerService::sendMessage);
    }
}
