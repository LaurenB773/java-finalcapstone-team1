package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.UserProfile;
import com.techelevator.model.Workout;

public interface UserProfileDao {

  /**
   * get user profile data
   * 
   * @return UserProfile
   */
  UserProfile getProfile(String username);

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
   * This function returns a list of workouts for a specific user identified by
   * their user ID.
   * 
   * @param userId The `userId` parameter is an integer value that represents the
   *               unique identifier of
   *               a user. This identifier is used to retrieve a list of workouts
   *               associated with that specific user.
   * @return A list of Workout objects for the specified userId.
   */
  List<Workout> getWorkouts(int userId);
}
