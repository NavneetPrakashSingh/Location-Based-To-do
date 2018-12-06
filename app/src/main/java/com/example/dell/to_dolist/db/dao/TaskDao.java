package com.example.dell.to_dolist.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.dell.to_dolist.TaskDisplay;
import com.example.dell.to_dolist.db.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("select * from task_table")
    List<Task> loadAllTasks();

    @Query("select creation_date from task_table where task_id = :taskId")
    String fetchDate(int taskId);

    @Query("select * from task_table order by priority asc")
    List<Task> sortTaskByPriority();

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM task_table where task_id = :taskId")
    void deleteTaskById(int taskId);

    @Query("SELECT task_id, task_title, count_status, total_count from task_table")
    LiveData<List<TaskDisplay>> getTaskList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTaskWithId(Task task);

    @Query("SELECT * FROM task_table WHERE task_id = :taskId")
    Task fetchTaskById(int taskId);

    @Query("SELECT * from task_table LIMIT 1")
    Task[] getAnyTask();

    @Query ("SELECT COUNT(id) / (select count(id) from subtask_table where maintask_id = :taskId) " +
            "from subtask_table where status='1' and maintask_id = :taskId")
    int countOfProgress(int taskId);

    @Query("UPDATE task_table SET count_status=:count, total_count= :total WHERE task_id = :taskId")
    void updateTaskCount(int count, int total, int taskId);

    @Query("UPDATE task_table SET image=:image WHERE task_id = :taskId")
    void updateTaskImage(byte[] image, int taskId);
    @Query("UPDATE task_table SET task_title=:title WHERE task_id = :taskId")
    void updateTaskTitle(String title, int taskId);

}
