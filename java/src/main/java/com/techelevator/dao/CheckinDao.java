package com.techelevator.dao;

import com.techelevator.model.Checkin;

public interface CheckinDao {

    /**
     * create new checkin
     */

    void checkin(int userId);
    /**
     * update checkin checout time
     */
    void checkOut(int userId);
    /**
     * gets most recent checkin to set state of isCheckedIn
     */
    boolean isCheckin(int userId);
}
