package com.techelevator.controller;

import com.techelevator.dao.JdbcProfileDao;
import com.techelevator.model.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MemberController {



    private JdbcProfileDao profileDao;

    public MemberController(JdbcProfileDao profileDao) {
        this.profileDao = profileDao;
    }


//    @RequestMapping(path = "/id", method = RequestMethod.GET)
//    public UserProfile getUserProfile(int id) {
//        return profileDao.getProfile(id);
//    }
}
