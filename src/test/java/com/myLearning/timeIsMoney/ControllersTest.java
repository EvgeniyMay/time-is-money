package com.myLearning.timeIsMoney;

import com.myLearning.timeIsMoney.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllersTest {

    @Autowired
    private ActivityController activityController;

    @Autowired
    private AuthorizationController authorizationController;

    @Autowired
    private MainController mainController;

    @Autowired
    private MissionController missionController;

    @Autowired
    private UserController userController;

    @Test
    public void activityControllerLoads() {
        assertThat(activityController).isNotNull();
    }

    @Test
    public void authorizationControllerLoads() {
        assertThat(authorizationController).isNotNull();
    }

    @Test
    public void mainControllerLoads() {
        assertThat(mainController).isNotNull();
    }

    @Test
    public void missionControllerLoads() {
        assertThat(missionController).isNotNull();
    }

    @Test
    public void userControllerLoads() {
        assertThat(userController).isNotNull();
    }

}