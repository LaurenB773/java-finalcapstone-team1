package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.UserProfile;

public interface UserProfileDao {

  /**
   * get user profile data
   * 
   * @return UserProfile
   */
  UserProfile getProfile(int id);

  /**
   * Updates user profile get by id and return new profile,
   * update in database
   * 
   * @param id
   * @param profileToUpdate
   * @return updatedUserProfile
   */

  UserProfile updateProfile(int id, UserProfile profileToUpdate);

  /**
   * deletes user profile, only admin or user can delete profile
   * 
   * @param id
   */
  void deleteProfile(int id);

  /**
   * creates new user when new account is created based on info
   * inserted by user
   * 
   * @param newProfile
   * @return createdProfile
   */
  UserProfile createProfile(UserProfile newProfile);

  /**
   * The function `getMembers()` returns a list of `UserProfile` objects.
   * 
   * @return A list of user profiles is being returned.
   */
  List<UserProfile> getMembers();
}
