package com.pironline.test.kafka;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.pironline.test.models.cdc.CompanyCdcEvent;
import com.pironline.test.models.cdc.EmployeeCdcEvent;
import com.pironline.test.services.HistoryLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final Gson gsonService;
    private final HistoryLogService historyLogService;

    @KafkaListener(
            topics = "${spring.kafka.topic.companies}",
            groupId = "${spring.kafka.groupId}",
            autoStartup = "${spring.kafka.consumer.listener.enabled}",
            concurrency = "${spring.kafka.consumer.listener.concurrency}",
            containerFactory = "pironlineListenerContainerFactory"
    )
    public void companyChangesListener(String data) {
        if (StringUtils.isBlank(data)) {
            return;
        }

        CompanyCdcEvent cdcEvent;
        try {
            cdcEvent = gsonService.fromJson(data, CompanyCdcEvent.class);
        } catch(final JsonSyntaxException ex) {
            log.error("Error while parsing company cdc json: ", ex);
            return;
        }
        historyLogService.save(cdcEvent, "companies");
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.employees}",
            groupId = "${spring.kafka.groupId}",
            autoStartup = "${spring.kafka.consumer.listener.enabled}",
            concurrency = "${spring.kafka.consumer.listener.concurrency}",
            containerFactory = "pironlineListenerContainerFactory"
    )
    public void employeeChangesListener(String data) {
        if (StringUtils.isBlank(data)) {
            return;
        }

        EmployeeCdcEvent cdcEvent;
        try {
            cdcEvent = gsonService.fromJson(data, EmployeeCdcEvent.class);
        } catch(final JsonSyntaxException ex) {
            log.error("Error while parsing employee cdc json: ", ex);
            return;
        }
        historyLogService.save(cdcEvent, "employees");
    }
}
