package com.tutorial.SpringTutorial.vo;

public class MemberResponse {

    private Long id;
    private String usrName;
    private String eMail;

    public MemberResponse(Long id, String usrName, String eMail) {
        this.id = id;
        this.usrName = usrName;
        this.eMail = eMail;
    }

    public Long getId() {
        return id;
    }

    public String getUsrName() {
        return usrName;
    }

    public String geteMail() {
        return eMail;
    }
}

