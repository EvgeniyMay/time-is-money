package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.ActivityDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.exception.ActivityAlreadyExistException;
import com.myLearning.timeIsMoney.exception.ObjectNotFoundException;
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

    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    public boolean create(ActivityDTO activityDTO) {
        Activity activity = Activity.builder()
                .name(activityDTO.getName())
                .description(activityDTO.getDescription())
                .build();
        try {
            activityRepository.save(activity);
            //ToDo
            // Log
            return true;
        } catch (Exception e) {
            //ToDo
            // Log
            // Localize error message
            throw new ActivityAlreadyExistException("Activity already exists");
        }
    }

    public Activity getById(Long id) {
        //ToDo
        // Localize error message
        return activityRepository.findById(id).orElseThrow(()->
                new ObjectNotFoundException("Activity not found"));
    }

    
    public boolean update(ActivityDTO activityDTO) {
        Activity activity = activityRepository.findById(activityDTO.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Activity not found"));

        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());

        try {
            activityRepository.save(activity);
            //ToDo
            // Log
            return true;
        } catch (Exception e) {
            //ToDo
            // Log
            // Localize error message
            throw new ActivityAlreadyExistException("Activity already exists");
        }
    }

    @Transactional
    public void deleteById(Long activityId) {
        missionRepository.deleteAllByActivityId(activityId);
        activityRepository.deleteById(activityId);
    }
}
