package com.myLearning.timeIsMoney.enums;

public enum MissionState {

    OFFERED("Offered"), GIVEN("Given"), DURING("During"), PASSED("Passed"), COMPLETED("Completed");


    private String name;

    MissionState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
