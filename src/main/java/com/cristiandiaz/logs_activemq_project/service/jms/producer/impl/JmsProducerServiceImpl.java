package com.cristiandiaz.logs_activemq_project.service.jms.producer.impl;

import com.cristiandiaz.logs_activemq_project.service.jms.producer.JmsProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JmsProducerServiceImpl implements JmsProducerService {

    @Value("${jms.queue.destination}")
    private String destination;
    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Object message) {
        log.info("Enviando mensaje a la cola.");
        jmsTemplate.convertAndSend(destination, message);
    }
}
