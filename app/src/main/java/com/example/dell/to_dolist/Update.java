package com.example.dell.to_dolist;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell.to_dolist.db.model.Task;

import java.util.ArrayList;

public class Update extends AppCompatActivity {

    private static final String DATABASE_NAME = "todo_database";
    private AppDatabase appDatabase = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();

    public int id =0;

    LinearLayout linearMain;
    CheckBox checkBox;
    EditText titleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle getId = getIntent().getExtras();
        if(getId != null){
            if(getId.getString("id") != null){
                id = Integer.valueOf(getId.getString("id"));
                //query over database and get the data
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        titleText = findViewById(R.id.title_text);
                        titleText.setTextSize(20);
                        Task taskDetails = appDatabase.taskModel().fetchTaskById(id);
                        titleText.setText(taskDetails.getTitle());
                        Log.i("0000000000000",String.valueOf(taskDetails.getContent()));


                        linearMain = findViewById(R.id.linear_main);
                        ArrayList<String> al = new ArrayList<String>();
                        al.add(taskDetails.getContent());
                        al.add("Banana");

                        for (int i = 0;i<al.size();i++){
                            checkBox = new CheckBox(Update.this);
                            checkBox.setTextSize(20);
                            checkBox.setId(i);
                            checkBox.setText(al.get(i));
                            checkBox.setOnClickListener(getOnSelectedItem(checkBox));
                            linearMain.addView(checkBox);
                        }

//                        Toast.makeText(getApplicationContext(),String.valueOf(taskDetails),Toast.LENGTH_SHORT).show();
                    }
                }).start();

            }
        }
    }

    View.OnClickListener getOnSelectedItem(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("On Click","Checkbox id:"+button.getId()+"--"+button.getText().toString() );
            }
        };
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.update_menu, menu );
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
