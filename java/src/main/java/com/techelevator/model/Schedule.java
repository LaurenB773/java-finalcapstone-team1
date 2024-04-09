package com.techelevator.model;

import java.time.LocalDateTime;

public class Schedule {
  private int scheduleId;
  private String title;
  private String instructor;
  private String description;
  private LocalDateTime classTime;
  private int duration;

  public Schedule() {

  }

  public Schedule(int scheduleId, String title, String instructor, String description, LocalDateTime classTime,
      int duration) {
    this.scheduleId = scheduleId;
    this.title = title;
    this.instructor = instructor;
    this.description = description;
    this.classTime = classTime;
    this.duration = duration;
  }

  public LocalDateTime getClassTime() {
    return classTime;
  }

  public void setClassTime(LocalDateTime classTime) {
    this.classTime = classTime;
  }

  public int getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(int scheduleId) {
    this.scheduleId = scheduleId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getInstructor() {
    return instructor;
  }

  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}
