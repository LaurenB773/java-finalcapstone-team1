package com.techelevator.model;

import java.time.LocalDateTime;

public class Checkin {
    private int checkinId;
    private int userId;
    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
    public Checkin() {

    }
    public Checkin(int checkinId, int userId, LocalDateTime checkinTime, LocalDateTime checkoutTime) {
      this.checkinId = checkinId;
      this.userId = userId;
      this.checkinTime = checkinTime;
      this.checkoutTime = checkoutTime;
    }
    public int getCheckinId() {
      return checkinId;
    }
    public void setCheckinId(int checkinId) {
      this.checkinId = checkinId;
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
      this.checkinTime = checkinTime;
    }
    public LocalDateTime getCheckoutTime() {
      return checkoutTime;
    }
    public void setCheckoutTime(LocalDateTime checkoutTime) {
      this.checkoutTime = checkoutTime;
    }
}
