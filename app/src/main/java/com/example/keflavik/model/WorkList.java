package com.example.keflavik.model;

public class WorkList {
    String workSuccess;
    Boolean workBoolean;
    String workTitle;
    String workAdress;
    String workTime;
    String workState;

    public WorkList(String workSuccess, Boolean workBoolean, String workTitle, String workAdress, String workTime, String workState) {
        this.workSuccess = workSuccess;
        this.workBoolean = workBoolean;
        this.workTitle = workTitle;
        this.workAdress = workAdress;
        this.workTime = workTime;
        this.workState = workState;
    }

    public String getWorkSuccess() {
        return workSuccess;
    }

    public void setWorkSuccess(String workSuccess) {
        this.workSuccess = workSuccess;
    }

    public Boolean getWorkBoolean() {
        return workBoolean;
    }

    public void setWorkBoolean(Boolean workBoolean) {
        this.workBoolean = workBoolean;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkAdress() {
        return workAdress;
    }

    public void setWorkAdress(String workAdress) {
        this.workAdress = workAdress;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkState() {
        return workState;
    }

    public void setWorkState(String workState) {
        this.workState = workState;
    }
}
