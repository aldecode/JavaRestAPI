package com.project.api.apilayer.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdatePasswordModel {
    @NotNull
    @Size(min = 8)

    @NotNull
    @Size(min = 8)
    private String oldPassword;

    @NotNull
    @Size(min = 8)
    private String newPassword;

    @NotNull
    @Size(min = 8)
    private String newPasswordCheck;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }

    public void setNewPasswordCheck(String newPasswordCheck) {
        this.newPasswordCheck = newPasswordCheck;
    }
}
