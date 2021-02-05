package com.myLearning.timeIsMoney.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO implements Serializable {

    @NotNull(message = "{input.error.empty_field")
    @Size(min=4, max = 20, message = "{input.error.login.size}")
    private String login;

    @NotNull(message = "{input.error.empty_field")
    @Size(min=8, max = 20, message = "{input.error.password.size}")
    private String password;

    public UserDTO() {
    }

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
