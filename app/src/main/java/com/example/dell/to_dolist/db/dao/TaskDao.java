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

    @Query("select * from task_table order by priority asc")
    List<Task> sortTaskByPriority();

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM task_table where task_id = :taskId")
    void deleteTaskById(int taskId);

/*    @Query("SELECT task_id, task_title from task_table")
    LiveData<List<TaskDisplay>> getTaskList();*/

    @Query("SELECT task_table.task_id, task_table.task_title, ( count(subtask_table.id) * 100.0 / (select count(subtask_table.id) from subtask_table)) AS count_status " +
            "FROM task_table inner join subtask_table " +
            "on task_table.task_id=subtask_table.maintask_id " +
            "and subtask_table.status='1'")
    LiveData<List<TaskDisplay>> getTaskList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTaskWithId(Task task);

    @Query("SELECT * FROM task_table WHERE task_id = :taskId")
    Task fetchTaskById(int taskId);

    @Query("SELECT * from task_table LIMIT 1")
    Task[] getAnyTask();


}
