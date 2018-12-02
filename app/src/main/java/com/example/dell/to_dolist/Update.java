package com.example.dell.to_dolist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell.to_dolist.db.model.Subtask;
import com.example.dell.to_dolist.db.model.Task;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public  class Update extends AppCompatActivity {

    private static final String DATABASE_NAME = "todo_database";
    private  AppDatabase appDatabase = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();

    public int id =0;

    LinearLayout linearMain;
    CheckBox checkBox;
    EditText titleText;
    ImageView displayImage;
    private byte[] dbFetchedbyte;
    private Bitmap photofinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        displayImage = findViewById(R.id.displayImageInUpdate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle getId = getIntent().getExtras();
        if(getId != null){
            if(getId.getString("id") != null){
                id = Integer.valueOf(getId.getString("id"));
                //query over database and get the data
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            titleText = findViewById(R.id.title_text);
                            titleText.setTextSize(20);
                            Task taskDetails = appDatabase.taskModel().fetchTaskById(id);
                            titleText.setText(taskDetails.getTitle());
                            Log.i("0000000000000",""+id);
                            linearMain = findViewById(R.id.linear_main);
                            ArrayList<String> al = new ArrayList<String>();
                            List<Subtask> subtasks=appDatabase.subTaskModel().loadAllSubTasks(id);
                            // al.add(taskDetails.getContent());
                            Log.i("0000000000000 :size",""+subtasks.size());
                            for(int i=0;i<subtasks.size();i++) //Jessica
                            {
                                al.add(subtasks.get(i).getTitle());
                                Log.i("0000000000000 :subtask",""+subtasks.get(i).getTitle()+" "+subtasks.get(i).getStatus()+" "+subtasks.get(i).getMainTaskKey());
                            }
                            for (int i = 0;i<al.size();i++){
                                checkBox = new CheckBox(Update.this);
                                checkBox.setTextSize(20);
                                checkBox.setId(i);
                                checkBox.setText(al.get(i));
                                checkBox.setOnClickListener(getOnSelectedItem(checkBox));
                                linearMain.addView(checkBox);
                            }
                            dbFetchedbyte = appDatabase.taskModel().fetchTaskById(id).getImage();

                            photofinal = getImage(dbFetchedbyte);
                            displayImage.setImageBitmap(photofinal);
//                        Toast.makeText(getApplicationContext(),String.valueOf(taskDetails),Toast.LENGTH_SHORT).show();
                        }catch(Exception ex){}

                    }
                }).start();

            }
        }


    }
    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    View.OnClickListener getOnSelectedItem(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("On Click","Checkbox id:"+button.getId()+"--"+button.getText().toString() );
            }
        };
    }

    public  void deleteTask(int id){
       final int deleteId= id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.taskModel().deleteTaskById(deleteId);
            }
        }).start();
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
            case R.id.action_delete:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase.taskModel().deleteTaskById(id);
                    }
                }).start();
               // Toast.makeText(Update.this,"Data deleted for id"+id,Toast.LENGTH_SHORT).show();

            case R.id.action_add_location:
                startActivity(new Intent(this,ChooseLocation.class));
                return true;


            case R.id.action_save:
                //Toast.makeText(Update.this,"Data Saved For id"+id,Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
