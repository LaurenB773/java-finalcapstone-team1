package com.techelevator.dao;

import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Scanner;

public interface ScheduleDao {
    /**
     * get all schedules
     * @return List <Schedule>
     */
    Schedule getAllSchedules(List<Schedule> schedules);

    /**
     * get a schedule by Id
     * @return Schedule
     */
    Schedule getSchedule(int id);

    /**
     * Updates schedule get by id and return new schedule,
     * update in database
     * @param id
     * @param scheduleToUpdate
     * @return updatedSchedule
     */

    UserProfile updateSchedule(int id, Schedule scheduleToUpdate);

    /**
     * deletes schedule
     * @param id
     */
    void deleteSchedule(int id);

    /**
     * creates new user when new account is created based on info
     * inserted by user
     * @param newSchedule
     * @return createdProfile
     */
    UserProfile createProfile(Schedule newSchedule);
}


