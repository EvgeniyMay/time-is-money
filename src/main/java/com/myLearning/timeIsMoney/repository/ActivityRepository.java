package com.myLearning.timeIsMoney.repository;

import com.myLearning.timeIsMoney.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findByName(String name);

    List<Activity> findActivitiesByArchivedIsFalse();
}
