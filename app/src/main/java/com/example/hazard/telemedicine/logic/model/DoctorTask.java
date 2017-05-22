package com.example.hazard.telemedicine.logic.model;

public class DoctorTask {

    private String complaintTitle;
    private String complaint;
    private String username;
    private String date;

    public DoctorTask() {
    }

    public DoctorTask(String complaintTitle, String complaint, String name, String date) {
        this.complaintTitle = complaintTitle;
        this.complaint = complaint;
        this.username = name;
        this.date = date;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
