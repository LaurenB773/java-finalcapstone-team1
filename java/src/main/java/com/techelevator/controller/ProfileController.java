package com.techelevator.controller;

import com.techelevator.dao.CheckinDao;
import com.techelevator.dao.CreateExerciseDto;
import com.techelevator.dao.ExerciseDao;
import com.techelevator.dao.UserProfileDao;

import com.techelevator.model.Equipment;
import com.techelevator.model.Exercise;
import com.techelevator.model.UserProfile;
import com.techelevator.model.Checkin;

import com.techelevator.security.dao.UserDao;
import com.techelevator.security.model.User;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

  private UserDao userDao;
  private UserProfileDao userProfileDao;
  private CheckinDao checkinDao;
  private ExerciseDao exerciseDao;

  public ProfileController(
    UserDao userDao,
    UserProfileDao userProfileDao,
    CheckinDao checkinDao,
    ExerciseDao exerciseDao
  ) {
    this.userDao = userDao;
    this.userProfileDao = userProfileDao;
    this.checkinDao = checkinDao;
    this.exerciseDao = exerciseDao;
  }

  /************************************* User Profile ******************************** */
  @GetMapping
  public UserProfile getUserProfile(Principal principal) {
    return userProfileDao.getProfile(getUserId(principal));
  }

  @PutMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Profile updated")
  public void updateProfile(Principal principal, @Valid @RequestBody UserProfile profileToUpdate) {
    userProfileDao.updateProfile(getUserId(principal), profileToUpdate);
  }

  @DeleteMapping
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Profile deleted")
  public void deleteProfile(Principal principal) {
    userProfileDao.deleteProfile(getUserId(principal));
  }

  /************************************* Exercises ******************************** */
  @GetMapping("/exercises")
  public List<Exercise> getExercises(Principal principal) {
    return exerciseDao.getExercises(getUserId(principal));
  }

  @GetMapping("/exercises/{id}")
  public List<Equipment> getEquipmentForExercise(@PathVariable int id){
    return exerciseDao.getEquipmentByExerciseId(id);
  }


  @PostMapping("/exercises")
  public Exercise createExercise(@RequestBody CreateExerciseDto dto, Principal principal){
    return exerciseDao.createExercise(dto, getUserId(principal));
  }

  @DeleteMapping("/exercises/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Exercise deleted")
  public void deleteExercise(@PathVariable int id) {
    exerciseDao.deleteExercise(id);
  }

  /************************************* Checkins ******************************** */
  @GetMapping("/checkins")
  public List<Checkin> getCheckins(Principal principal) {
    return checkinDao.getCheckins(getUserId(principal));
  }

  @GetMapping("/checkin")
  public boolean isCheckedIn(Principal principal) {
    return checkinDao.isCheckin(getUserId(principal));
  }

  @PostMapping("/checkin")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User checked in")
  public void checkUserIn(Principal principal) {
    checkinDao.checkin(getUserId(principal));
  }

  @PutMapping("/checkout")
  @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User checked out")
  public void checkUserOut(Principal principal) {
    checkinDao.checkOut(getUserId(principal));
  }

  private int getUserId(Principal principal) {
    String username = principal.getName();
    User user = userDao.getUserByUsername(username);
    return user.getId();
  }

}
