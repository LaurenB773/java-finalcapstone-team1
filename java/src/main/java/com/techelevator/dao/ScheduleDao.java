package com.techelevator.dao;

import com.techelevator.model.Schedule;
import com.techelevator.model.UserProfile;
import com.techelevator.security.model.User;

import java.util.List;

public interface ScheduleDao {
    /**
     * get all schedules
     * @return List <Schedule>
     */
    List<Schedule> getAllSchedules();

    /**
     * get a schedule by Id
     * @return Schedule
     */
    Schedule getSchedule(int id);

    /**
     * Updates schedule get by id and return new schedule,
     * update in database
     *
     * @param id
     * @param scheduleToUpdate
     * @return updatedSchedule
     */

    Schedule updateSchedule(Schedule scheduleToUpdate, int id);

    /**
     * deletes schedule
     * @param id
     */
    void deleteSchedule(int id);

    /**
     * creates new schedule when new schedule is created based on info
     * inserted by user
     *
     * @param newSchedule
     * @return createdSchedule
     */
    Schedule createSchedule(Schedule newSchedule);

    void addMemberToScheduledEvent(User principalUser, int id);

    List<UserProfile> getSignedUpMembers(int scheduleId);

    List<Schedule> getSignedUpEvents(int id);

    void removeMemberFromEvent(int userId, int id);
}


