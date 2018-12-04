/*Created by Sharon Alva on 30/10/2018
* Separate POJO to display Task on the screen
* */

package com.example.dell.to_dolist;


import android.arch.persistence.room.ColumnInfo;

public class TaskDisplay {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setId(int _id){this.id = _id;}
    public int getId(){return id;}

    @ColumnInfo(name = "task_title")
    private String title;

    @ColumnInfo(name = "task_id")
    private int id;

}

