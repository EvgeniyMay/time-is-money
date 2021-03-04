package com.myLearning.timeIsMoney;

import com.myLearning.timeIsMoney.repository.ActivityRepository;
import com.myLearning.timeIsMoney.repository.MissionRepository;
import com.myLearning.timeIsMoney.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RepositoriesTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void activityRepositoryLoads() {
        assertThat(activityRepository).isNotNull();
    }

    @Test
    public void missionRepositoryLoads() {
        assertThat(missionRepository).isNotNull();
    }

    @Test
    public void userRepositoryLoads() {
        assertThat(userRepository).isNotNull();
    }
}
