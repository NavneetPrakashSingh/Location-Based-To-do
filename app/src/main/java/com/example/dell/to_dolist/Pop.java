package com.example.dell.to_dolist;


import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dell.to_dolist.db.model.Subtask;
import com.example.dell.to_dolist.db.model.Task;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class Pop extends AppCompatActivity {


    private static final String DATABASE_NAME = "todo_database";
    private AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView reminder;
    private Button btn;
    private Button btnCancel;
    private Button submit;
    private Button cancelTask;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private byte[] photobyteDB;
    private ImageView displayImage;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Referencing variable
        final EditText editTxt1 = findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        submit = (Button) findViewById(R.id.submit);
        cancelTask = (Button) findViewById(R.id.deleteButton);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        reminder = (TextView) findViewById(R.id.reminder);
        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        displayImage = (ImageView) findViewById(R.id.displayImage);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice, arrayList);
        list.setAdapter(adapter);


        //Add subtask activity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // View view1 = (LayoutInflater.from(Pop.this)).inflate(R.layout.user_input, null);
                //setting up alert dialogue for adding subtask
               final AlertDialog.Builder builder2 = new AlertDialog.Builder(Pop.this);
                LayoutInflater inflater = Pop.this.getLayoutInflater();
                //setting user_input layout for alert dialogue
                View view1 = inflater.inflate(R.layout.user_input, null);
                final EditText userInput1 = (EditText) view1.findViewById(R.id.userinput);
                builder2.setCancelable(false);

                //onclick listener for CANCEL button
                builder2.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                //onclick listener for OK button
                builder2.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                              //  String tag = userInput1.getText().toString().trim();
                                if (userInput1.getText().toString().trim().length()==0) {

                                    Toast.makeText(getApplicationContext(), "Enter a valid Subtask", Toast.LENGTH_SHORT).show();

                                }  else {
                                    String tag1 = userInput1.getText().toString();
                                    // this line adds the data of your EditText and puts in your array
                                    arrayList.add(tag1);
                                    // next thing you have to do is check if your adapter has changed
                                    adapter.notifyDataSetChanged();
                                    // dismiss your alert.
                                }
                            }
                        });

                builder2.setView(view1);
                builder2.create().show();

            }
        });

        //Delete subtask activity
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SparseBooleanArray positioncheck = list.getCheckedItemPositions();
                int count = list.getCount();

                //iterare to subtasks
                for (int i = count - 1; i >= 0; i--) {
                    if (positioncheck.get(i)) {
                        adapter.remove(arrayList.get(i));
                    }
                }
                positioncheck.clear();

                //update pop view
                adapter.notifyDataSetChanged();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if main task is empty
                if (TextUtils.isEmpty(editTxt1.getText().toString())) {
                    editTxt1.setError("Please enter the main task");

                } else {

                    //Add data to db
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String titleValue = String.valueOf(editTxt1.getText());
                            //Adding data to db
                            Task task = new Task(titleValue,date, "1", "2/2/3", "1", "1", "1", photobyteDB, 0, 0);
                            long id = appDatabase.taskModel().insertTaskWithId(task);
                            Log.d("*****************", "taskID: " + id); //Jessica
                            long subId;
                            int count = 0;
                            int totalCount = 0;
                            SparseBooleanArray sparseBooleanArray = list.getCheckedItemPositions();
                            Subtask sTask;
                            for (int i = 0; i < arrayList.size(); i++) {
                                if (sparseBooleanArray.get(i)) {
                                    sTask = new Subtask(arrayList.get(i), (int) id, 1);
                                    count++;
                                } else {
                                    sTask = new Subtask(arrayList.get(i), (int) id, 0);

                                }
                                subId = appDatabase.subTaskModel().insertSubTask(sTask);
                                totalCount++;
                                Log.d("*****************", "subtaskID: " + subId); //Jessica
                            }

                            appDatabase.taskModel().updateTaskCount(count, totalCount, (int) id);

                        }
                    }).start();
                    startActivity(new Intent(Pop.this, MainActivity.class));
                }
            }
        });

        // fuction to cancel adding task
        cancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        //For calender
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = month + "/" + day + "/" + year;
                reminder.setText("Reminder set for "+date);
                reminder.setVisibility(View.VISIBLE);
            }
        };

        Bundle getValueBack = getIntent().getExtras();
        if (getValueBack != null) {
            if (getValueBack.getString("Location") != null) {
                Toast.makeText(this, getValueBack.getString("Location"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    //To choose Location
    public void selectLocation(View view) {
        startActivity(new Intent(this, ChooseLocation.class));
    }

    //Use camera fuctionality
    private void camera() {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);
    }

//Choose a category function
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    displayImage.setImageBitmap(photo);
                    //Uri selectedImage = imageReturnedIntent.getData();
                    // displayImage.setImageURI(selectedImage);
                    byte[] photobyte = getBytes(photo);
                    photobyteDB = photobyte;
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    displayImage.setImageURI(selectedImage);
                }
                break;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

// display location , camera , calender in sequence
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_set_reminder:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Pop.this,
                        android.R.style.Theme_DeviceDefault_Dialog,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();
                // Toast.makeText(Update.this,"Data deleted for id"+id,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_add_location:
                startActivity(new Intent(this, ChooseLocation.class));
                return true;
            case R.id.action_add_image:
                //Toast.makeText(Update.this,"Data Saved For id"+id,Toast.LENGTH_SHORT).show();
                final CharSequence[] items = {"Take Photo",
                        "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Pop.this);
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





