/* Updated by Sharon Alva on 30/10/2018
* Changes related to displaying task list
* */

package com.example.dell.to_dolist;

import android.app.NotificationManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.to_dolist.db.model.Reminder;
import com.example.dell.to_dolist.db.model.Task;
import com.example.dell.to_dolist.db.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private TextView txtView;
    public static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;

    private TaskViewModel mTaskViewModel;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Bundle getId = getIntent().getExtras();

        setContentView(R.layout.activity_main);
        mDb = AppDatabase.getDatabase(getApplicationContext());
//        TextView txtView=findViewById(R.task_id.txtView);
        //populateDb();
       // fetchData();
        showNotification();


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskListAdapter adapter = new TaskListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTaskViewModel= ViewModelProviders.of(this).get(TaskViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTaskViewModel.getmAllTasks().observe(this, new Observer<List<TaskDisplay>>() {
            @Override
            public void onChanged(@Nullable final List<TaskDisplay> tasks) {
                // Update the cached copy of the words in the adapter.
                adapter.setTasks(tasks);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        TaskDisplay myTask = adapter.getTaskAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myTask.getTitle(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mTaskViewModel.deleteTask(myTask);
                    }
                });

        helper.attachToRecyclerView(recyclerView);




        add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                //startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);

                startActivity(new Intent(MainActivity.this,Pop.class));


            }
        });

        showNotification();



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/*
        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Task task = new Task(data.getStringExtra(AddTaskActivity.TITLE), data.getStringExtra(AddTaskActivity.CONTENT),
                    "30/10/2018","1","","","1","");
            mTaskViewModel.insert(task);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Task not saved because it is empty.",
                    Toast.LENGTH_LONG).show();
        } */
    }
    private void showNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Wallmart Reminder")
                .setContentText("Milk")
                .setContentText("Banana")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }

    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
		
		if(id == R.id.about)
		{
			Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
		}

        /*if (id == R.id.clear_data) {
            // Add a toast just for confirmation
            Toast.makeText(this, "Clearing the data...",
                    Toast.LENGTH_SHORT).show();

            // Delete the existing data
            mTaskViewModel.deleteAll();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


 /*Commented by Sharon

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }*/

    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
        List<Reminder> reminders = mDb.reminderModel().findReminderByTaskId("1");
        List<User> users = mDb.userModel().findUserByUserId("1");
        for (Reminder reminder : reminders) {
            Log.d("abc", "fetchData: inside");
            Log.d("abc", "fetchData: task_id :" + reminder.latitude);
            for (User user : users) {
                if((user.user_latitude.equalsIgnoreCase(reminder.latitude)) && (user.user_longitude.equalsIgnoreCase(reminder.longitude)) )
                showNotification();
            }
        }
        List<Task> tasks=mDb.taskModel().sortTaskByPriority();
        Toast.makeText(this,""+tasks.get(0).getTitle() +" "+tasks.get(1).getTitle() +" "+tasks.get(2).getTitle(),Toast.LENGTH_SHORT).show();
    }


}
