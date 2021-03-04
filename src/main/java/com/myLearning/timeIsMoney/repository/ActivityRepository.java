package com.myLearning.timeIsMoney.repository;

import com.myLearning.timeIsMoney.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findActivitiesByIsArchivedIsFalse();

    Page<Activity> findActivitiesByIsArchivedIs(boolean isArchived, Pageable pageable);
}
