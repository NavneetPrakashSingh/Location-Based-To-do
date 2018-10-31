package com.example.dell.to_dolist.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String user_id;
    public String user_latitude;
    public String user_longitude;
}
