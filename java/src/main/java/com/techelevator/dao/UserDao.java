package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserProfile;
import com.techelevator.model.auth.RegisterUserDto;

import java.util.List;

public interface UserDao {

  List<User> getUsers();

  User getUserById(int id);

  User getUserByUsername(String username);

  User createUser(RegisterUserDto user);

}
