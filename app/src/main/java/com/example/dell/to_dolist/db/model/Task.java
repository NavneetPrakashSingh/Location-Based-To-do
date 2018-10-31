package com.example.dell.to_dolist.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int task_id;

    @ColumnInfo(name = "task_title")
    private String title;

    @ColumnInfo(name = "task_content")
    private String content;
    @ColumnInfo(name = "creation_date")
    private String creationDate;
    /*@ColumnInfo(name = "taskName")
    private String taskName;*/
    @ColumnInfo(name = "priority")
    private String priority;
    @ColumnInfo(name = "updateDate")
    private String updateDate;
    @ColumnInfo(name = "rem_flag")
    private String remFlag;
    @ColumnInfo(name = "taskStatus")
    private String taskStatus;
    @ColumnInfo(name = "locatonFlag")
    private String locatonFlag;

    public Task(String title, String content, String creationDate, String priority, String updateDate, String remFlag, String taskStatus, String locatonFlag) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        /*this.taskName = taskName;*/
        this.priority = priority;
        this.updateDate = updateDate;
        this.remFlag = remFlag;
        this.taskStatus = taskStatus;
        this.locatonFlag = locatonFlag;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
/*
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }*/

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemFlag() {
        return remFlag;
    }

    public void setRemFlag(String remFlag) {
        this.remFlag = remFlag;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getLocatonFlag() {
        return locatonFlag;
    }

    public void setLocatonFlag(String locatonFlag) {
        this.locatonFlag = locatonFlag;
    }
}
