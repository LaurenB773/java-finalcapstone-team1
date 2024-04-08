package com.techelevator.model;

public class UserProfile {
  private String firstName;
  private String lastName;
  private String email;
  private String goal;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  private int profileId;
  private int userId;
  private String profilePicture;

  public UserProfile() {

  }

  public UserProfile(int profileId, int userId, String profilePicture) {
    this.profileId = profileId;
    this.userId = userId;
    this.profilePicture = profilePicture;
  }

  public int getProfileId() {
    return profileId;
  }

  public void setProfileId(int profileId) {
    this.profileId = profileId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

}
