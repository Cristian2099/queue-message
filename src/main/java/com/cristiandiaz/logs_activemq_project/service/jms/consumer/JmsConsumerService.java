package com.cristiandiaz.logs_activemq_project.service.jms.consumer;

public interface JmsConsumerService {

    /**
     * Método encargado de escuchar mensajes desde la cola e imprimir su valor
     * @param queueMessageStr mensaje recibido desde la cola
     */
    void listenQueueMessage(String queueMessageStr);
}
