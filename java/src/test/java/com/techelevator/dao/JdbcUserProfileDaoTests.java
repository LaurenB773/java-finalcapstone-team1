package com.techelevator.dao;

import com.techelevator.model.UserProfile;
import com.techelevator.security.dao.JdbcUserDao;
import com.techelevator.security.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcUserProfileDaoTests extends  BaseDaoTests{
 private JdbcUserProfileDao sut;

    private static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER");
    private static final User USER_2 = new User(3, "employee", "employee", "ROLE_EMPLOYEE");
 private static final UserProfile PROFILE_1 = new UserProfile("name1","last1","user1@gmail.com","weight loss",1,1,null);
 private static final UserProfile PROFILE_2 = new UserProfile("name2","last2","user2@gmail.com","strength",2,2,null);
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserProfileDao(jdbcTemplate);
    }


   @Test
    public void getProfile_give_valid_profile (){
        UserProfile userProfile = sut.getProfile(1);

        Assert.assertEquals(PROFILE_1.getFirstName(),userProfile.getFirstName());
        Assert.assertEquals(PROFILE_1.getLastName(),userProfile.getLastName());
        Assert.assertEquals(PROFILE_1.getEmail(),userProfile.getEmail());
        Assert.assertEquals(PROFILE_1.getGoal(),userProfile.getGoal());
        Assert.assertEquals(PROFILE_1.getUserId(),userProfile.getUserId());
        Assert.assertEquals(PROFILE_1.getProfileId(),userProfile.getProfileId());
   }
   @Test
    public void createProfile_creates_a_profile(){
       UserProfile userProfile = new UserProfile();
       userProfile.setFirstName("first3");
       userProfile.setLastName("last3");
       userProfile.setEmail("user3@gmail.com");
       userProfile.setGoal("weight loss");

       UserProfile createdProfile = sut.createProfile(userProfile, 3);
       Assert.assertNotNull(createdProfile);
   }

  @Test
  public void updateProfile_updates_profile(){
      UserProfile userProfile = new UserProfile();
      userProfile.setFirstName("first3");
      userProfile.setLastName("last3");
      userProfile.setEmail("user3@gmail.com");
      userProfile.setGoal("weight loss");

      sut.updateProfile(2,userProfile);

  }
}
