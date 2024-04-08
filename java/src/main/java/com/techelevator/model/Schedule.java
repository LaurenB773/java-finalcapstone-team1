package com.techelevator.model;

import java.time.LocalDate;

public class Schedule {
    private int scheduleId;
    private String title;
    private String instructor;
    private String description;
    private LocalDate time;
    private LocalDate date;
    private LocalDate duration;

        public Schedule() {
        }

        public Schedule(int scheduleID, String title, String instructor, String description, LocalDate time, LocalDate date, LocalDate duration) {
            this.scheduleId = scheduleID;
            this.title = title;
            this.instructor = instructor;
            this.description = description;
            this.time = time;
            this.date = date;
            this.duration = duration;
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

        public LocalDate getTime() {
            return time;
        }

        public void setTime(LocalDate time) {
            this.time = time;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public LocalDate getDuration() {
            return duration;
        }

        public void setDuration(LocalDate duration) {
            this.duration = duration;
        }
    }

