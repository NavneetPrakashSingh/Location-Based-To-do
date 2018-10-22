package com.example.dell.to_dolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface TaskDao {

    @Query("select * from Task")
    List<Task> loadAllTasks();

    @Query("select * from Task order by priority asc")
    List<Task> sortTaskByPriority();

    @Query("DELETE FROM Task")
    void deleteAllTasks();

    @Insert(onConflict = IGNORE)
    void insertTask(Task task);

}
