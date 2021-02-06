package com.myLearning.timeIsMoney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    @NotNull(message = "{input.error.empty_field")
    @Size(min=4, max = 20, message = "{input.error.login.size}")
    private String login;

    @NotNull(message = "{input.error.empty_field")
    @Size(min=8, max = 20, message = "{input.error.password.size}")
    private String password;
}
