package com.example.dell.to_dolist;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Reminder {
    @PrimaryKey
    @NonNull
    public String rem_id;
    public String task_id;
    public String latitude;
    public String longitude;
    public String date;
    public String frequency;

}
