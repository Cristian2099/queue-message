package com.cristiandiaz.logs_activemq_project.service.log;

import com.cristiandiaz.logs_activemq_project.dto.QueueMessageDTO;

public interface LogService {

    /**
     *
     * @param message required to send message log in queue
     */
    void sendLog(QueueMessageDTO message) throws Exception;
}
