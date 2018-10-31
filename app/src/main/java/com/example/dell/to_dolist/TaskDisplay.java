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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ColumnInfo(name = "task_title")
    private String title;

    @ColumnInfo(name = "task_content")
    private String content;
}

