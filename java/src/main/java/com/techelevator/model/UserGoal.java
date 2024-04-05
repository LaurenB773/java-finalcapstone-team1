package com.techelevator.model;

public class UserGoal {
  private String goal;
  private int userId;

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public UserGoal(String goal, int userId) {
    this.goal = goal;
    this.userId = userId;
  }

  public UserGoal() {

  }
}
