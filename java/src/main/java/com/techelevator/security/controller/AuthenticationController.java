package com.techelevator.security.controller;

import javax.validation.Valid;

import com.techelevator.dao.CheckinDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserProfileDao;
import com.techelevator.security.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;
import com.techelevator.security.model.LoginDto;
import com.techelevator.security.model.LoginResponseDto;
import com.techelevator.security.model.RegisterUserDto;
import com.techelevator.security.model.User;

@RestController
@CrossOrigin
public class AuthenticationController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private UserDao userDao;
  private UserProfileDao userProfileDao;

  private CheckinDao checkinDao;

  public AuthenticationController(TokenProvider tokenProvider,
      AuthenticationManagerBuilder authenticationManagerBuilder,
      UserDao userDao, UserProfileDao userProfileDao, CheckinDao checkinDao) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.userDao = userDao;
    this.userProfileDao = userProfileDao;
    this.checkinDao = checkinDao;
  }

  @RequestMapping(path = "/login", method = RequestMethod.POST)
  public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginDto.getUsername(), loginDto.getPassword());

    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.createToken(authentication, false);

    User user;
    try {
      user = userDao.getUserByUsername(loginDto.getUsername());
    } catch (DaoException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect.");
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(path = "/register", method = RequestMethod.POST)
  public void register(@Valid @RequestBody RegisterUserDto newUser) {

    try {
      User user = userDao.createUser(newUser);
      if (user == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User registration failed.");
      }

      // Create a user profile when registering a new user
      UserProfile createdUserProfile = new UserProfile();
      createdUserProfile.setFirstName(newUser.getFirstName());
      createdUserProfile.setLastName(newUser.getLastName());
      createdUserProfile.setEmail(newUser.getEmail());
      createdUserProfile.setGoal(newUser.getGoal());

      userProfileDao.createProfile(createdUserProfile, user.getId());

      checkinDao.checkin(user.getId());
      checkinDao.checkOut(user.getId());
    } catch (DaoException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
    }
  }

}
