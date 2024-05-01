package com.pironline.test.repositories;

import com.pironline.test.persistences.HistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryLogRepository extends JpaRepository<HistoryLog, Long> {
    
}
