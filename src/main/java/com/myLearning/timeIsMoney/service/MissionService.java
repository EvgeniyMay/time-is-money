package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.entity.Mission;
import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.enums.MissionState;
import com.myLearning.timeIsMoney.exception.DurationLessThanZeroException;
import com.myLearning.timeIsMoney.exception.ObjectNotFoundException;
import com.myLearning.timeIsMoney.repository.ActivityRepository;
import com.myLearning.timeIsMoney.repository.MissionRepository;
import com.myLearning.timeIsMoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository, UserRepository userRepository, ActivityRepository activityRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    public List<Mission> getAll() {
        return missionRepository.findAll();
    }


    @Transactional
    public boolean createMission(Long userId, MissionDTO missionDTO) {
        validateDuration(missionDTO);

        Mission mission = Mission.builder()
                .user(User.builder().id(userId).build())
                .activity(Activity.builder().id(missionDTO.getActivityId()).build())
                .startTime(htmlDate2LocalDateTime(missionDTO.getStartTimeString()))
                .endTime(htmlDate2LocalDateTime(missionDTO.getEndTimeString()))
                .state(MissionState.GIVEN)
                .build();
        try {
            missionRepository.save(mission);
            //ToDO
            // Log
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public boolean offerMission(Long userId, MissionDTO missionDTO) {
        validateDuration(missionDTO);

        Mission mission = Mission.builder()
                .user(User.builder().id(userId).build())
                .activity(Activity.builder().id(missionDTO.getActivityId()).build())
                .startTime(htmlDate2LocalDateTime(missionDTO.getStartTimeString()))
                .endTime(htmlDate2LocalDateTime(missionDTO.getEndTimeString()))
                .state(MissionState.OFFERED)
                .build();
        try {
            missionRepository.save(mission);
            //ToDO
            // Log
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    public boolean passMission(Long missionId) {
        return changeMissionState(missionId, MissionState.PASSED);
    }
    public boolean completeMission(Long missionId) {
        return changeMissionState(missionId, MissionState.COMPLETED);
    }

    @Transactional
    public boolean changeMissionState(Long missionId, MissionState state) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(ObjectNotFoundException::new);
        mission.setState(state);

        try {
            missionRepository.save(mission);
            //ToDO
            // Log
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private LocalDateTime htmlDate2LocalDateTime(String htmlInputData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(htmlInputData, formatter);
    }

    private void validateDuration(MissionDTO missionDTO){
        if(missionDTO.getStartTimeString().compareTo(missionDTO.getEndTimeString()) > 0) {
            throw new DurationLessThanZeroException();
        }
    }
}
