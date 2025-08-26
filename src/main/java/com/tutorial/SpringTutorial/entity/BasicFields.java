package com.tutorial.SpringTutorial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
@MappedSuperclass
public class BasicFields {

    /* common fields */

    @Column(name = "create_date_time")
    private Long createDateTime;

    @Column(name = "create_user")
    private Long createUser;

    @Column(name = "update_date_time")
    private Long updateDateTime;

    @Column(name = "update_user")
    private Long updateUser;

    @Version
    @Column(name = "version")
    private Integer version = 1;

//    @FuzzyField 自定義的 (尚未定義)
    @Column(name = "keyword_search")
    private String keywordSearch = "";

    /* getters and setters */

    public Long getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Long createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Long updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getKeywordSearch() {
        return keywordSearch;
    }

    public void setKeywordSearch(String keywordSearch) {
        this.keywordSearch = keywordSearch;
    }

}
