package com.techelevator.security.dao;

<<<<<<< HEAD:java/src/main/java/com/techelevator/security/dao/UserDao.java
import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.security.model.User;
=======
import com.techelevator.model.User;
import com.techelevator.model.UserProfile;
import com.techelevator.model.auth.RegisterUserDto;
>>>>>>> 45031a91ae9cabba0db806b4bada7dc5d1d776ea:java/src/main/java/com/techelevator/dao/UserDao.java

import java.util.List;

public interface UserDao {

  List<User> getUsers();

  User getUserById(int id);

  User getUserByUsername(String username);

  User createUser(RegisterUserDto user);

}
