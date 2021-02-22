package com.myLearning.timeIsMoney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MissionDTO {

    private Long userId;

    private Long activityId;

    @NotEmpty(message = "{input.error.empty_field}")
    private String startTimeString;
    @NotEmpty(message = "{input.error.empty_field}")
    private String endTimeString;
}
