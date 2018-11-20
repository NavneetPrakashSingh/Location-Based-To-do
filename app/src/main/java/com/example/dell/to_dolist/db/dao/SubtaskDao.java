package com.example.dell.to_dolist.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.example.dell.to_dolist.db.model.Subtask;

@Dao
public interface SubtaskDao {


    //queries using subtask is done here
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubTask(Subtask subtask);
}

