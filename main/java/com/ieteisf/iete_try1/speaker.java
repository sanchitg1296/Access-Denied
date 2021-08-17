package com.ieteisf.iete_try1;

public class speaker {

    String Name,Description,MeetingLink,Photo,Date,Time,Status,Designation,social;

    speaker(){}

    public speaker(String name, String description, String meetingLink, String photo, String date, String time, String designation, String social) {
        this.Name = name;
        this.Description = description;
        this.MeetingLink = meetingLink;
        this.Photo = photo;
        this.Date = date;
        this.Time = time;
        this.Designation = designation;
        this.social = social;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getMeetingLink() {
        return MeetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.MeetingLink = meetingLink;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        this.Photo = photo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    /*public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }*/

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        this.Designation = designation;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }
}
