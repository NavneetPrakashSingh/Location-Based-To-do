package com.example.dell.to_dolist;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        TextView txtView=findViewById(R.id.txtView);
        populateDb();
        fetchData();

    }

    private void showNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("To-do List")
                .setContentText("Pick up groceries")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }

    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
        List<Reminder> reminders = mDb.reminderModel().findReminderByTaskId("1");
        List<User> users = mDb.userModel().findUserByUserId("1");
        for (Reminder reminder : reminders) {
            Log.d("abc", "fetchData: inside");
            Log.d("abc", "fetchData: id :" + reminder.latitude);
            for (User user : users) {
                if((user.user_latitude.equalsIgnoreCase(reminder.latitude)) && (user.user_longitude.equalsIgnoreCase(reminder.longitude)) )
                showNotification();
            }
            /*sb.append(String.format(Locale.US,
                    "%s, %s (%s)\n", reminder.latitude, reminder.longitude, reminder.frequency));*/
        }
        //txtView.setText(sb);
    }
}
