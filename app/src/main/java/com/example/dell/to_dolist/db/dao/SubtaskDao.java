package com.example.dell.to_dolist.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.dell.to_dolist.db.model.Subtask;

import java.util.List;

@Dao
public interface SubtaskDao {


    //queries using subtask is done here
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSubTask(Subtask subtask);

    @Query("select * from subtask_table where maintask_id=:maintask_id")
    List<Subtask> loadAllSubTasks(int maintask_id);

    @Query("UPDATE subtask_table SET status = :checkboxValue WHERE id = :id")
    void updateSubtask(int checkboxValue, int id);

    @Query("DELETE FROM subtask_table where maintask_id=:maintask_id")
    void deleteSubtasks(int maintask_id);
}

