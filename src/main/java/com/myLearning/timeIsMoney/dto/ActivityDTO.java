package com.myLearning.timeIsMoney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ActivityDTO {

    @NotEmpty(message = "{input.error.empty_field}")
    private String name;

    @NotEmpty(message = "{input.error.empty_field}")
    private String description;
}
