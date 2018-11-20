package com.example.dell.to_dolist.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "subtask_table")
public class Subtask {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "subtask_title")
    private String title;

    @ColumnInfo(name = "maintask_id")
    private int mainTaskKey;

    @ColumnInfo(name = "status")
    private int status;

    public Subtask(){

    }

    public Subtask(String _title,int _mainTaskId, int _status){
        this.title = _title;
        this.mainTaskKey = _mainTaskId;
        this.status = _status;
    }

    public int getId() {
        return id;
    }

    public void setId(int task_id) {
        this.id = task_id;
    }

    public Integer getStatus(){return status;}
    public void setStatus(Integer _status){this.status = _status;}

    public Integer getMainTaskKey(){return status;}
    public void setMainTaskKey(Integer _status){this.status = _status;}

    public String getTitle(){return title;}
    public void setTitle(String _title){this.title = _title;}

}
