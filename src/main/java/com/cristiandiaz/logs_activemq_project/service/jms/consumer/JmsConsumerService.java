package com.cristiandiaz.logs_activemq_project.service.jms.consumer;

public interface JmsConsumerService {

    /**
     *
     * @param queueMessageStr mensaje recibido desde la cola
     */
    void listenQueueMessage(String queueMessageStr);
}
