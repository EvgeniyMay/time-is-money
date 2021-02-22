package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.entity.Mission;
import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.enums.MissionState;
import com.myLearning.timeIsMoney.exception.DurationLessThanZeroException;
import com.myLearning.timeIsMoney.repository.ActivityRepository;
import com.myLearning.timeIsMoney.repository.MissionRepository;
import com.myLearning.timeIsMoney.repository.UserRepository;
import com.myLearning.timeIsMoney.util.LocalDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Page<Mission> getPageableByState(MissionState state, Pageable pageable) {
        return missionRepository.findMissionsByState(state, pageable);
    }

    public boolean createMission(MissionDTO missionDTO, MissionState state) {
        validateDuration(missionDTO);

        Mission mission = Mission.builder()
                .user(User.builder().id(missionDTO.getUserId()).build())
                .activity(Activity.builder().id(missionDTO.getActivityId()).build())
                .startTime(LocalDateUtil.parseHtmlDate(missionDTO.getStartTimeString()))
                .endTime(LocalDateUtil.parseHtmlDate(missionDTO.getEndTimeString()))
                .state(state)
                .build();
        try {
            missionRepository.save(mission);
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public boolean updateMissionState(Mission mission, MissionState state) {
        mission.setState(state);

        try {
            missionRepository.save(mission);
            return true;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void validateDuration(MissionDTO missionDTO){
        if(missionDTO.getStartTimeString().compareTo(missionDTO.getEndTimeString()) > 0) {
            throw new DurationLessThanZeroException();
        }
    }
}
