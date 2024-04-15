package com.techelevator.model;

import java.time.LocalDateTime;

public class Checkin {
    private int id;

    private int userId;

    private LocalDateTime checkinTime;

    private LocalDateTime checkoutTime;

    public Checkin() {
    }

    public Checkin(int id, int userId, LocalDateTime checkinTime, LocalDateTime checkoutTime) {
        this.id = id;
        this.userId = userId;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = LocalDateTime.now();
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = LocalDateTime.now();
    }
}
