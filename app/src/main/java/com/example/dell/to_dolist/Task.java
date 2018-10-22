package com.example.dell.to_dolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Task {
    @PrimaryKey
    @NonNull
    String id;
    String creation_date;
    String task_name;
    String priority;
    String update_date;
    String rem_id;
    String task_status;
    String locaton_flag;
}
