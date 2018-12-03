package com.example.dell.to_dolist;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.dell.to_dolist.db.dao.ReminderDao;
import com.example.dell.to_dolist.db.dao.SubtaskDao;
import com.example.dell.to_dolist.db.dao.TaskDao;
import com.example.dell.to_dolist.db.dao.UserDao;
import com.example.dell.to_dolist.db.model.Reminder;
import com.example.dell.to_dolist.db.model.Subtask;
import com.example.dell.to_dolist.db.model.Task;
import com.example.dell.to_dolist.db.model.User;

@Database(entities = {Reminder.class, User.class, Task.class, Subtask.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase INSTANCE;

    public abstract ReminderDao reminderModel();
    public abstract UserDao userModel();
    public abstract TaskDao taskModel();
    public abstract SubtaskDao subTaskModel();

  /*  public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }*/

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "todo_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TaskDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.taskModel();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            mDao.deleteAllTasks();
//
//          Task task = new Task("Grocery List","Milk","1/2/3","1","2/2/3","1","1","1");
//           mDao.insertTask(task);
////
//            Task task1 = new Task("Shopping List","Dress","1/2/3","0","2/2/3","2","1","1");
//           mDao.insertTask(task1);
            return null;
        }
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
