package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.MissionDTO;
import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.entity.Mission;
import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.enums.MissionState;
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

    // TODO
    // NO SERVICE/REFACTOR
    // Builder
    public Mission createMission(Long userId, MissionDTO missionDTO) {
        Mission mission = new Mission();

        mission.setStartTime(htmlDataInputToLocalDateTime(missionDTO.getStartTimeString()));
        mission.setEndTime(htmlDataInputToLocalDateTime(missionDTO.getEndTimeString()));

        // NOT HERE
        mission.setState(MissionState.GIVEN);

        return missionRepository.save(mission);
    }

    public Mission passMission(Long missionId) {
        return changeMissionState(missionId, MissionState.PASSED);
    }
    public Mission completeMission(Long missionId) {
        return changeMissionState(missionId, MissionState.COMPLETED);
    }

    // TODO
    // LOOK AT THIS
    @Transactional
    private Mission changeMissionState(Long missionId, MissionState state) {
        // THROWS EXCEPTION
        Mission mission = missionRepository.findById(missionId).get();
        mission.setState(state);

        return missionRepository.save(mission);
    }

    private LocalDateTime htmlDataInputToLocalDateTime(String htmlInputData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        return LocalDateTime.parse(htmlInputData, formatter);
    }
}
