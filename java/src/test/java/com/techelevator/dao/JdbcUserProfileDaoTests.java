package com.techelevator.dao;

import com.techelevator.model.UserProfile;
import com.techelevator.security.dao.JdbcUserDao;
import com.techelevator.security.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


public class JdbcUserProfileDaoTests extends  BaseDaoTests{
 private JdbcUserProfileDao sut;

 private static final UserProfile PROFILE_1 = new UserProfile("first1","last1","user1@gmail.com","weight loss",1,1,null);
 private static final UserProfile PROFILE_2 = new UserProfile("first2","last2","user2@gmail.com","strength",2,2,null);
 private static final UserProfile PROFILE_3 = new UserProfile("first3","last3","user3@gmail.com","body building",3,3,null);
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserProfileDao(jdbcTemplate);
    }


   @Test
    public void getProfile_give_valid_profile (){
        UserProfile userProfile1 = sut.getProfile(1);
        assertUserProfileMatch(PROFILE_1,userProfile1);

        UserProfile userProfile2 = sut.getProfile(2);
        assertUserProfileMatch(PROFILE_2,userProfile2);
   }
   @Test
    public void createProfile_creates_a_profile(){
       UserProfile userProfile = new UserProfile();
       userProfile.setFirstName("first3");
       userProfile.setLastName("last3");
       userProfile.setEmail("user3@gmail.com");
       userProfile.setGoal("body building");


       UserProfile createdProfile = sut.createProfile(userProfile, 3);
       Assert.assertNotNull(createdProfile);
       assertUserProfileMatch(PROFILE_3,createdProfile);
   }

  @Test
  public void updateProfile_updates_profile(){
      UserProfile userProfile = new UserProfile();
      userProfile.setFirstName("first1");
      userProfile.setLastName("last1");
      userProfile.setEmail("user1@gmail.com");
      userProfile.setGoal("weight loss");

      sut.updateProfile(2,userProfile);
      UserProfile updatedProfile = sut.getProfile(2);
     assertUserProfileMatch(userProfile,updatedProfile);
  }

  @Test
    public void deleteProfile_deletes_profile(){
        sut.deleteProfile(2);
        UserProfile userProfile = sut.getProfile(2);
        Assert.assertNull(userProfile);
  }
    private void assertUserProfileMatch(UserProfile expectedProfile, UserProfile actualProfile) {
        Assert.assertEquals(expectedProfile.getFirstName(), actualProfile.getFirstName());
        Assert.assertEquals(expectedProfile.getLastName(), actualProfile.getLastName());
        Assert.assertEquals(expectedProfile.getEmail(), actualProfile.getEmail());
        Assert.assertEquals(expectedProfile.getGoal(), actualProfile.getGoal());
    }




}
