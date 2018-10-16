package com.example.dell.to_dolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ReminderDao {
    @Query("select * from Reminder")
    List<Reminder> loadAllReminders();

    @Query("select * from Reminder where rem_id = :rem_id")
    Reminder loadReminderById(String rem_id);

    @Query("select * from Reminder where task_id = :task_id")
    List<Reminder> findReminderByTaskId(String task_id);

    @Insert(onConflict = IGNORE)
    void insertReminder(Reminder reminder);

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("DELETE FROM Reminder where task_id=:task_id")
    void deleteReminderBasedOnTask(String task_id);

    @Query("DELETE FROM Reminder")
    void deleteAllReminder();
}