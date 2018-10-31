package com.example.dell.to_dolist;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private TextView txtView;
    private FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Pop.class));


            }
        });


        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
//        TextView txtView=findViewById(R.id.txtView);
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
        }
        List<Task> tasks=mDb.taskModel().sortTaskByPriority();
        Toast.makeText(this,""+tasks.get(0).task_name+" "+tasks.get(1).task_name+" "+tasks.get(2).task_name,Toast.LENGTH_SHORT).show();
    }
}
