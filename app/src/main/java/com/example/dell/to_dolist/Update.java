package com.example.dell.to_dolist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.to_dolist.db.model.Subtask;
import com.example.dell.to_dolist.db.model.Task;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.dell.to_dolist.Pop.getImage;
public  class Update extends AppCompatActivity {

    private static final String DATABASE_NAME = "todo_database";
    private  AppDatabase appDatabase = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public int id =0;
    private Button btn;
    private Button btnCancel;
    private Button submit;
    CheckBox checkBox;
    EditText titleText;
    ImageView displayImage;
    private byte[] dbFetchedbyte;
    private Bitmap photofinal;
    private ListView linearMain;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private ArrayList<String> addList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        displayImage = findViewById(R.id.displayImageInUpdate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn = (Button) findViewById(R.id.button);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        submit = (Button) findViewById(R.id.submit);
        linearMain = findViewById(R.id.linear_main);
        //list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        addList = new ArrayList<String>();
        Bundle getId = getIntent().getExtras();
        if (getId != null) {
            if (getId.getString("id") != null) {
                id = Integer.valueOf(getId.getString("id"));
                //query over database and get the data
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            titleText = findViewById(R.id.title_text);
                            titleText.setTextSize(20);
                            Task taskDetails = appDatabase.taskModel().fetchTaskById(id);
                            titleText.setText(taskDetails.getTitle());
                            Log.i("0000000000000", "" + id);
                            linearMain = findViewById(R.id.linear_main);
                            //ArrayList<String> al = new ArrayList<String>();
                            List<Subtask> subtasks = appDatabase.subTaskModel().loadAllSubTasks(id);
                            // al.add(taskDetails.getContent());
                            Log.i("0000000000000 :size", "" + subtasks.size());
                            for (int i = 0; i < subtasks.size(); i++) //Jessica
                            {
                                arrayList.add(subtasks.get(i).getTitle());
                                if(subtasks.get(i).getStatus()==1)
                                    linearMain.setItemChecked(i,true);
                                else
                                    linearMain.setItemChecked(i,false);
                                adapter.notifyDataSetChanged();
                                Log.i("0000000000000 :subtask", "" + subtasks.get(i).getTitle() + " " + subtasks.get(i).getStatus() + " " + subtasks.get(i).getMainTaskKey());
                            }
                            dbFetchedbyte = appDatabase.taskModel().fetchTaskById(id).getImage();

                            photofinal = getImage(dbFetchedbyte);
                            displayImage.setImageBitmap(photofinal);
//                        Toast.makeText(getApplicationContext(),String.valueOf(taskDetails),Toast.LENGTH_SHORT).show();
                        } catch (Exception ex) {
                        }

                    }
                }).start();

            }
            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_multiple_choice, arrayList);

            linearMain.setAdapter(adapter);
            linearMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                  @Override
                                                  public void onItemClick(AdapterView<?> parent, View view, int position, long idTask) {
                                                      appDatabase.subTaskModel().deleteSubtasks(id);
                                                      SparseBooleanArray sparseBooleanArray = linearMain.getCheckedItemPositions();
                                                      Subtask sTask;
                                                      long subId;
                                                      for (int i = 0; i < arrayList.size(); i++) {
                                                          if (sparseBooleanArray.get(i)) {
                                                              Log.d("*****************", "status 1: " + arrayList.get(i)); //Jessica
                                                              sTask = new Subtask(arrayList.get(i), (int) id, 1);
                                                          } else {
                                                              Log.d("*****************", "status 0: " + arrayList.get(i)); //Jessica
                                                              sTask = new Subtask(arrayList.get(i), (int) id, 0);
                                                          }
                                                          subId=appDatabase.subTaskModel().insertSubTask(sTask);
                                                          Log.d("*****************", "subtaskID: " + subId); //Jessica
                                                      }
                                                      //String item = (String) linearMain.getItemAtPosition(position);
                                                      //Log.d("CHECKING ITEM", "onItemClick: "+item);

                                                  }
                                              }
            );

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // View view1 = (LayoutInflater.from(Pop.this)).inflate(R.layout.user_input, null);
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(Update.this);
                    LayoutInflater inflater = Update.this.getLayoutInflater();
                    ViewGroup parent = null;
                    View view1 = inflater.inflate(R.layout.user_input, parent, false);
                    final EditText userInput1 = (EditText) view1.findViewById(R.id.userinput);
                    builder2.setView(view1);

                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int idValue) {
                            String tag = userInput1.getText().toString();
                            // this line adds the data of your EditText and puts in your array
                            arrayList.add(tag);
                            //addList.add(tag);
                            Subtask sTask;
                            sTask = new Subtask(arrayList.get(arrayList.size()-1), (int) id, 0);
                            long subId = appDatabase.subTaskModel().insertSubTask(sTask);
                            Log.d("CHECK***********", "subtaskID: " +arrayList.get(arrayList.size()-1)+":"+ subId+" taskID: "+id);
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
                    long subId;
                    int count = 0;
                    int totalCount = 0;
                    appDatabase.subTaskModel().deleteSubtasks(id);
                    SparseBooleanArray positioncheck = linearMain.getCheckedItemPositions();
                    count = linearMain.getCount();
                    for (int i = count - 1; i >= 0; i--) {
                        if (positioncheck.get(i)) {
                            adapter.remove(arrayList.get(i));
                        }
                    }
                    positioncheck.clear();
                    adapter.notifyDataSetChanged();
                    SparseBooleanArray sparseBooleanArray = linearMain.getCheckedItemPositions();
                    Subtask sTask;
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (sparseBooleanArray.get(i)) {
                            sTask = new Subtask(arrayList.get(i), (int) id, 1);
                            count++;
                        } else {
                            sTask = new Subtask(arrayList.get(i), (int) id, 0);
                        }
                        subId=appDatabase.subTaskModel().insertSubTask(sTask);
                        totalCount++;
                        Log.d("*****************", "subtaskID: " + subId); //Jessica
                    }
                    appDatabase.taskModel().updateTaskCount(count, totalCount, (int) id);
                }
            });
        }
    }
    //Use camera fuctionality
    private void camera() {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);
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
                startActivity(new Intent(Update.this, MainActivity.class));
                return true;

            case R.id.action_add_location:
                startActivity(new Intent(this, ChooseLocation.class));
                return true;
            case R.id.action_add_image:
                //Toast.makeText(Update.this,"Data Saved For id"+id,Toast.LENGTH_SHORT).show();
                final CharSequence[] items = {"Take Photo",
                        "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
                builder.setTitle("Choose Category");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (items[item].equals("Take Photo")) {
                            camera();
                        }  else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
                return true;

            default:
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                return true;
        }
    }



}
