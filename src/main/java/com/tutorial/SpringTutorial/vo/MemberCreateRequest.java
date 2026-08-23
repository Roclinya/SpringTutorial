package com.tutorial.SpringTutorial.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberCreateRequest {

    @NotBlank(message = "usrName is required")
    private String usrName;

    @NotBlank(message = "eMail is required")
    @Email(message = "eMail format is invalid")
    private String eMail;

    @NotBlank(message = "usrPwd is required")
    private String usrPwd;

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }
}

