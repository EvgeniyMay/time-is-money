package com.myLearning.timeIsMoney.dto;

import com.myLearning.timeIsMoney.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MissionDTO {

//    private Long userId;
    @NotNull(message = "{input.error.empty_field}")
    private User user;

    @NotNull(message = "{input.error.empty_field}")
    private Long activityId;

    @NotEmpty(message = "{input.error.empty_field}")
    private String startTimeString;
    @NotEmpty(message = "{input.error.empty_field}")
    private String endTimeString;
}
