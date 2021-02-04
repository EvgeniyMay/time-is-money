package com.myLearning.timeIsMoney.dto;

import java.time.LocalDateTime;

public class MissionDTO {

    private Long userId;
    private Long activityId;

    private String startTimeString;
    private String endTimeString;


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getStartTimeString() {
        return startTimeString;
    }
    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }
    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }
}
