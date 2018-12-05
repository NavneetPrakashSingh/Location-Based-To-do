package com.example.dell.to_dolist;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.to_dolist.db.model.Subtask;
import com.example.dell.to_dolist.db.model.Task;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public  class Update extends AppCompatActivity {

    private static final String DATABASE_NAME = "todo_database";
    private  AppDatabase appDatabase = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

    public int id =0;
    private Button btn;
    private Button btnCancel;
    //LinearLayout linearMain;
    CheckBox checkBox;
    EditText titleText;
    ImageView displayImage;
    private byte[] dbFetchedbyte;
    private Bitmap photofinal;
    private ListView linearMain;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        displayImage = findViewById(R.id.displayImageInUpdate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (Button) findViewById(R.id.button);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        linearMain = findViewById(R.id.linear_main);
        //list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        Bundle getId = getIntent().getExtras();
        if(getId != null){
            if(getId.getString("id") != null) {
                id = Integer.valueOf(getId.getString("id"));
            }

        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice, arrayList);

        linearMain.setAdapter(adapter);


        try{
            titleText = findViewById(R.id.title_text);
            Task taskDetails = appDatabase.taskModel().fetchTaskById(id);
            titleText.setText(taskDetails.getTitle());
            Log.i("0000000000000",""+id);
            //ArrayList<String> al = new ArrayList<String>();
            List<Subtask> subtasks=appDatabase.subTaskModel().loadAllSubTasks(id);
            // al.add(taskDetails.getContent());
            for(int i=0;i<subtasks.size();i++) //Jessica
            {
                arrayList.add(subtasks.get(i).getTitle());
                adapter.notifyDataSetChanged();
                Log.i("0000000000000 :subtask",""+subtasks.get(i).getTitle()+" "+subtasks.get(i).getStatus()+" "+subtasks.get(i).getMainTaskKey());
            }
            for (int i = 0;i<arrayList.size();i++){
                Log.d("hghgh", "run: inside");
                checkBox = new CheckBox(Update.this);
                checkBox.setTextSize(20);
                checkBox.setId(subtasks.get(i).getId());
                if(subtasks.get(i).getStatus()==0)
                    checkBox.setChecked(false);
                else
                    checkBox.setChecked(true);
                checkBox.setText(arrayList.get(i));
                checkBox.setOnClickListener(getOnSelectedItem(checkBox));
                //linearMain.addView(checkBox);
            }
            dbFetchedbyte = appDatabase.taskModel().fetchTaskById(id).getImage();

            photofinal = getImage(dbFetchedbyte);
            displayImage.setImageBitmap(photofinal);
//                        Toast.makeText(getApplicationContext(),String.valueOf(taskDetails),Toast.LENGTH_SHORT).show();
        }catch(Exception ex){}





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // View view1 = (LayoutInflater.from(Pop.this)).inflate(R.layout.user_input, null);

                AlertDialog.Builder builder2 = new AlertDialog.Builder(Update.this);
                LayoutInflater inflater = Update.this.getLayoutInflater();
                ViewGroup parent = null;
                View view1 = inflater.inflate(R.layout.user_input, parent , false);
                final EditText userInput1 = (EditText) view1.findViewById(R.id.userinput);
                builder2.setView(view1);

                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String tag = userInput1.getText().toString();
                        // this line adds the data of your EditText and puts in your array
                        arrayList.add(tag);
                        // next thing you have to do is check if your adapter has changed
                        adapter.notifyDataSetChanged();
                    }
                })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .show();
            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SparseBooleanArray positioncheck = linearMain.getCheckedItemPositions();

                int count = linearMain.getCount();

                for(int i = count -1; i>=0; i--) {
                    if (positioncheck.get(i)) {

                        adapter.remove(arrayList.get(i));

                    }
                }
                positioncheck.clear();

                adapter.notifyDataSetChanged();
            }
        });




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


    View.OnClickListener getOnSelectedItem(final CheckBox checkbox) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkbox.isChecked())
                {
                    Log.i("On Click","CHECKED:"+checkbox.getId()+"--"+checkbox.getText().toString());
                    appDatabase.subTaskModel().updateSubtask(1,checkbox.getId());
                }
                else
                {
                    Log.i("On Click","UNCHECKED:"+checkbox.getId()+"--"+checkbox.getText().toString());
                    appDatabase.subTaskModel().updateSubtask(0,checkBox.getId());
                }
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
