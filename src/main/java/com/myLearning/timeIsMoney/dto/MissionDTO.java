package com.myLearning.timeIsMoney.dto;

import com.myLearning.timeIsMoney.entity.Activity;
import com.myLearning.timeIsMoney.entity.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {

    private Long userId;

    private Long activityId;

    @NotEmpty(message = "{input.error.empty_field}")
    private String startTimeString;

    @NotEmpty(message = "{input.error.empty_field}")
    private String endTimeString;


    private List<Activity> ableActivities;

    private List<User> ableUsers;
}
