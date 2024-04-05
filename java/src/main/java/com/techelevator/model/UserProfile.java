package com.techelevator.model;

public class UserProfile {
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
