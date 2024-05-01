package com.pironline.test.services;

import com.pironline.test.persistences.HistoryLog;
import com.pironline.test.repositories.HistoryLogRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryLogServiceTxn {

    private final HistoryLogRepository historyLogRepository;

    @Transactional
    public void save(List<HistoryLog> historyLogs) {
        historyLogRepository.saveAll(historyLogs);
    }
}
