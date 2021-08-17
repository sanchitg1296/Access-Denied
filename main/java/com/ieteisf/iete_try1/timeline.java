package com.ieteisf.iete_try1;

public class timeline {

    private String StartTime;
    private String EndTime;
    private String Link;
    private String EventName;
    private String SpeakerPhoto;
    private int Status;
    private String social;

    timeline(){}


    public timeline(String startTime, String endTime, String link, String eventName, String speakerPhoto, int status, String social) {
        this.StartTime = startTime;
        this.EndTime = endTime;
        this.Link = link;
        this.EventName = eventName;
        this.SpeakerPhoto = speakerPhoto;
        this.Status = status;
        this.social = social;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        this.StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        this.EndTime = endTime;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        this.Link = link;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        this.EventName = eventName;
    }

    public String getSpeakerPhoto() {
        return SpeakerPhoto;
    }

    public void setSpeakerPhoto(String speakerPhoto) {
        this.SpeakerPhoto = speakerPhoto;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }


    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }
}
