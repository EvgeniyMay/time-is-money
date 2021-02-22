package com.myLearning.timeIsMoney.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class UsersAndActivities {

    private List<User> users;

    private List<Activity> activities;

}
