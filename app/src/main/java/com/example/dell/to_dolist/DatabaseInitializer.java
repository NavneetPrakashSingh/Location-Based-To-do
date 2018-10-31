package com.example.dell.to_dolist;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.dell.to_dolist.db.model.Reminder;
import com.example.dell.to_dolist.db.model.Task;
import com.example.dell.to_dolist.db.model.User;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Loan insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    /*private static void addLoan(final AppDatabase db, final String id,
                                final Reminder reminder, final Book book, Date from, Date to) {
        Loan loan = new Loan();
        loan.id = id;
        loan.bookId = book.id;
        loan.userId = user.id;
        loan.startTime = from;
        loan.endTime = to;
        db.loanModel().insertLoan(loan);
    }
*/
    private static Reminder addReminder(final AppDatabase db, final String rem_id, final String task_id, final String latitude, final String longitude, final String date, final String frequency) {
        Reminder reminder = new Reminder();
        reminder.rem_id = rem_id;
        reminder.task_id = task_id;
        reminder.latitude=latitude;
        reminder.longitude=longitude;
        reminder.date="";
        reminder.frequency="1";
        db.reminderModel().insertReminder(reminder);
        return reminder;
    }
    /*
    Commented by Sharon
    private static Task addTask(final AppDatabase db, final String id, final String creation_date, final String task_name, final String priority,
                                final String update_date, final String rem_id, final String task_status, final String locaton_flag) {
        Task task = new Task();
        task.setId(rem_id);
        task.setPriority(priority);
        task.setCreationDate(creation_date);
        task.setTaskName(task_name);
        task.setUpdateDate(update_date);
        task.setReminderId(rem_id);
        task.setTaskStatus(task_status);
        task.setLocatonFlag(locaton_flag);
        db.taskModel().insertTask(task);
        return task;
    }*/
    private static User addUser(final AppDatabase db, final String user_id, final String user_latitude, final String user_longitude) {
        User user = new User();
        user.user_id = user_id;
        user.user_latitude=user_latitude;
        user.user_longitude=user_longitude;
        db.userModel().insertUser(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db) {
        db.reminderModel().deleteAllReminder();
        db.userModel().deleteAllUser();
        Reminder reminder1 = addReminder(db, "1", "1", "1.2333", "1.38989", "","1");
        Reminder reminder2 = addReminder(db, "2", "1", "3.4983", "5.39989", "", "2");
        Reminder reminder3 = addReminder(db, "3", "1", "1.7874", "2.54345","","6");
      /*
      * Commented by Shraon Alva on 30/10/2018
      *
      Task task1 = addTask(db, "1", "1/2/3", "Groceries", "1", "2/2/3","1","1","1");
        Task task2 = addTask(db, "2", "1/2/3", "PickUp", "0", "2/2/3", "2","1","1");
        Task task3 = addTask(db, "3", "1/2/3", "List", "1","2/2/3","6","1","1");*/

       /* try {
            // Loans are added with a delay, to have time for the UI to react to changes.



        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        User user1 = addUser(db, "1", "1.2333", "1.38989");
        //User user2 = addReminder(db, "2", "1", "3.4983", "5.39989", "", "2");
        //User user3 = addReminder(db, "3", "1", "1.7874", "2.54345","","6");

    }

    /*  private static Date getTodayPlusDays(int daysAgo) {
          Calendar calendar = Calendar.getInstance();
          calendar.add(Calendar.DATE, daysAgo);
          return calendar.getTime();
      }
  */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}