/*Created by Sharon Alva on 30/10/2018
* Separate POJO to display Task on the screen
* */

package com.example.dell.to_dolist;
import android.arch.persistence.room.ColumnInfo;

/*
* Sample Input: Various operations related to the task is being used
* Sample Output: Task related to the operations are performed
* */

public class TaskDisplay {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setId(int _id){this.id = _id;}
    public int getId(){return id;}

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @ColumnInfo(name = "task_title")
    private String title;

    @ColumnInfo(name = "task_id")
    private int id;

    @ColumnInfo(name = "count_status")
    private double count;


    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }

    @ColumnInfo(name = "total_count")
    private double totalCount;
}

