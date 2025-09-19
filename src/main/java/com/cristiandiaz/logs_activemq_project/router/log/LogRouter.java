package com.cristiandiaz.logs_activemq_project.router.log;

import com.cristiandiaz.logs_activemq_project.dto.QueueMessageDTO;
import com.cristiandiaz.logs_activemq_project.router.log.base.LogRouterBase;
import com.cristiandiaz.logs_activemq_project.service.log.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogRouter extends LogRouterBase {

    private final LogService logService;

    @PostMapping("/log")
    public ResponseEntity<HttpStatus> sendLog(@RequestBody QueueMessageDTO queueMessage) throws Exception {
        logService.sendLog(queueMessage);
        return ResponseEntity.ok().build();
    }
}
