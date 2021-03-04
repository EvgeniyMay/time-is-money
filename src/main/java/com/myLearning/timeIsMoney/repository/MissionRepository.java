package com.myLearning.timeIsMoney.repository;

import com.myLearning.timeIsMoney.entity.Mission;
import com.myLearning.timeIsMoney.enums.MissionState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findMissionsByState(MissionState state, Pageable pageable);
}
