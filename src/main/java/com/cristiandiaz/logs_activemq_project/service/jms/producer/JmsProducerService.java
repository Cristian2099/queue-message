package com.cristiandiaz.logs_activemq_project.service.jms.producer;

public interface JmsProducerService {

    /**
     *
     * @param message to send message in queue
     */
    void sendMessage(Object message);
}
