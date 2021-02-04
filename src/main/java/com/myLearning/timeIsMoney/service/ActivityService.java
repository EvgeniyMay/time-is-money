package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.ActivityDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.repository.ActivityRepository;
import com.myLearning.timeIsMoney.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final MissionRepository missionRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, MissionRepository missionRepository) {
        this.activityRepository = activityRepository;
        this.missionRepository = missionRepository;
    }

    public Activity create(ActivityDTO activityDTO) {
        // Builder
        Activity activity = new Activity();
        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());

        return activityRepository.save(activity);
    }

    @Transactional
    public Activity update(ActivityDTO activityDTO) {
        // ADD THROWS
        Activity activity = activityRepository.findById(activityDTO.getId()).get();
        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());

        return activityRepository.save(activity);
    }

    public Activity getById(Long id) {
        // ADD THROWS
        return activityRepository.findById(id).get();
    }

    /// void ???
    @Transactional
    public void deleteById(Long activityId) {
        // ADD THROWS
        missionRepository.deleteAllByActivityId(activityId);
        activityRepository.deleteById(activityId);
    }

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }
}
