package com.myLearning.timeIsMoney;

import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.service.ActivityService;
import com.myLearning.timeIsMoney.service.MissionService;
import com.myLearning.timeIsMoney.service.UserDetailsServiceImpl;
import com.myLearning.timeIsMoney.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServicesTest {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MissionService missionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;


    @Test
    public void activityServiceLoads() {
        assertThat(activityService).isNotNull();
    }

    @Test
    public void missionServiceLoads() {
        assertThat(missionService).isNotNull();
    }
    @Test
    public void userDetailsServiceLoads() {
        assertThat(userDetailsService).isNotNull();
    }
    @Test
    public void userServiceLoads() {
        assertThat(userService).isNotNull();
    }
}
