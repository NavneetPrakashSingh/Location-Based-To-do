package com.example.dell.to_dolist.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "subtask_table")
public class Subtask {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "subtask_title")
    private String title;

    @ColumnInfo(name = "status")
    private int status;

    public Subtask(){

    }

    public Integer getStatus(){return status;}
    public void setStatus(Integer _status){this.status = _status;}

    public String getTitle(){return title;}
    public void setTitle(String _title){this.title = _title;}

}
