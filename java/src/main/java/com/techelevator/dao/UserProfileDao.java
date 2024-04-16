package com.techelevator.dao;

import com.techelevator.model.UserProfile;
import java.util.List;

public interface UserProfileDao {
  /**
   * get user profile data
   *
   * @return UserProfile
   */
  UserProfile getProfile(int userId);

  /**
   * Updates user profile get by id and return new profile,
   * update in database
   *
   * @param userId
   * @param profileToUpdate
   * @return updatedUserProfile
   */

  void updateProfile(int userId, UserProfile profileToUpdate);

  /**
   * deletes user profile, only admin or user can delete profile
   *
   * @param userId
   */
  void deleteProfile(int userId);

  /**
   * creates new user when new account is created based on info
   * inserted by user
   *
   * @param newProfile
   * @return createdProfile
   */
  UserProfile createProfile(UserProfile newProfile, int userId);

  /**
   * The function `getMembers()` returns a list of `UserProfile` objects.
   *
   * @return A list of user profiles is being returned.
   */
  List<UserProfile> getMembers();


  /**
   * This function returns a list of user profiles representing employees.
   * 
   * @return A list of UserProfile objects representing employees.
   */
  List<UserProfile> getEmployees();
}
