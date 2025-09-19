package com.cristiandiaz.logs_activemq_project.service.jms.producer;

public interface JmsProducerService {

    /**
     * Método encargado de enviar mensajes a la cola.
     * @param message to send message in queue
     */
    void sendMessage(Object message);
}
