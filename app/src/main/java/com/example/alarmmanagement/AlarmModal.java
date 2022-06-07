package com.example.alarmmanagement;
public class AlarmModal {

    private String date;
    private String minutes;
    private String hour;
    private String description;
    private int id;

    public String getDate() {
        return date;
    }

    public void setCourseName(String courseName) {
        this.date = courseName;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlarmModal(String courseName, String courseDuration, String courseTracks, String courseDescription) {
        this.date = courseName;
        this.minutes = courseDuration;
        this.hour = courseTracks;
        this.description = courseDescription;
    }
}
