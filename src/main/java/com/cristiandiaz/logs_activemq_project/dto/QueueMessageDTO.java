package com.cristiandiaz.logs_activemq_project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueueMessageDTO {
    private int id;
    private String name;
    private String description;
}
