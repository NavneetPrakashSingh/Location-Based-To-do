package com.example.dell.to_dolist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface UserDao {
    @Query("select * from User")
    List<User> loadAllUsers();

    @Query("select * from User where user_id=:user_id")
    User loadUserById(String user_id);

    @Query("select * from User where user_id=:user_id")
    List<User> findUserByUserId(String user_id);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM User where user_id=:user_id")
    void deleteReminderBasedOnUserID(String user_id);

    @Query("DELETE FROM User")
    void deleteAllUser();
}
