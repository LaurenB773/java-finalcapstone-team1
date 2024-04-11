package com.techelevator.dao;

import com.techelevator.model.Schedule;

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
}


