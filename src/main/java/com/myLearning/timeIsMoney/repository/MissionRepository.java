package com.myLearning.timeIsMoney.repository;

import com.myLearning.timeIsMoney.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    void deleteAllByActivityId(Long activityId);

}
