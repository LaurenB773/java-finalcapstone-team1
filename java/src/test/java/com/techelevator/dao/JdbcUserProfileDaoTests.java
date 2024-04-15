package com.techelevator.dao;

import com.techelevator.model.UserProfile;
import com.techelevator.security.dao.JdbcUserDao;
import com.techelevator.security.model.User;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserProfileDaoTests extends  BaseDaoTests{
 private JdbcUserProfileDao sut;
    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER");
 private static final UserProfile PROFILE_1 = new UserProfile("name1","last1","user1@gmail.com","weight loss",1,1,null);
 private static final UserProfile PROFILE_2 = new UserProfile("name2","last2","user2@gmail.com","weight loss",2,3,null);
    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserProfileDao(jdbcTemplate);
    }

    


}
