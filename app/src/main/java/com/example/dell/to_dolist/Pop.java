package com.example.dell.to_dolist;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.dell.to_dolist.db.model.Subtask;
import com.example.dell.to_dolist.db.model.Task;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class Pop extends AppCompatActivity {

    //private static final String TAG = "Pop";
    private static final String DATABASE_NAME = "todo_database";
    private AppDatabase appDatabase = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();


    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private Button btn;
    private Button btnCancel;
    private Button submit;
    private  Button cancelTask;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private byte[] photobyteDB;
    private byte[] dbFetchedbyte;
    private Bitmap photofinal;
    /*private ProgressBar mprogress;*/
    private ImageView displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText editTxt1 = findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        submit = (Button) findViewById(R.id.submit);
        cancelTask = (Button)findViewById(R.id.deleteButton);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        displayImage = (ImageView) findViewById(R.id.displayImage);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice, arrayList);

        list.setAdapter(adapter);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;
        int height = metric.heightPixels;

        //  getWindow().setLayout((int) (width * 0.7), (int) (height * 0.7));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // View view1 = (LayoutInflater.from(Pop.this)).inflate(R.layout.user_input, null);
                Log.d("I AM UNIQUE in pop", "onClick: ");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(Pop.this);
                LayoutInflater inflater = Pop.this.getLayoutInflater();
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


                SparseBooleanArray positioncheck = list.getCheckedItemPositions();

                int count = list.getCount();

                for(int i = count -1; i>=0; i--) {
                    if (positioncheck.get(i)) {

                        adapter.remove(arrayList.get(i));

                    }
                }
                positioncheck.clear();

                adapter.notifyDataSetChanged();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                
                   if (TextUtils.isEmpty(editTxt1.getText().toString())) {

                   editTxt1.setError("Please enter the main task");

               }
               
                else
               {

                String value ="";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String titleValue = String.valueOf(editTxt1.getText());

                        Task task = new Task(titleValue,"1/2/3","1","2/2/3","1","1","1", photobyteDB,0,0);
                        long id=appDatabase.taskModel().insertTaskWithId(task);
                        Log.d("*****************", "taskID: "+id); //Jessica
                        long subId;
                        int k;
                        int count=0;
                        int totalCount=0;
                        SparseBooleanArray sparseBooleanArray = list.getCheckedItemPositions();
                        Subtask sTask;
                        for (int i=0;i<arrayList.size();i++) {
                            if(sparseBooleanArray.get(i))
                            {   sTask = new Subtask(arrayList.get(i),(int) id,1);
                                count++;
                            }
                            else {
                                sTask = new Subtask( arrayList.get( i ), (int) id, 0 );
                                
                            }
                            subId=appDatabase.subTaskModel().insertSubTask(sTask);
                            totalCount++;
                            Log.d("*****************", "subtaskID: "+subId); //Jessica
                        }

                        appDatabase.taskModel().updateTaskCount( count,totalCount,(int)id );
                        
                    }
                }).start();
                startActivity(new Intent(Pop.this,MainActivity.class));
               }
            }
        });


        cancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;

            }
        };

        Bundle getValueBack = getIntent().getExtras();
        if (getValueBack != null) {
            if (getValueBack.getString("Location") != null) {
                Toast.makeText(this,getValueBack.getString("Location"),Toast.LENGTH_SHORT).show();
//                TextView locationText = (TextView) findViewById(R.id.LocationText);
//                locationText.setText(getValueBack.getString("Location"));
            }
        }


    }

    public void selectLocation(View view){
        startActivity(new Intent(this,ChooseLocation.class));
    }


    private void camera()
    {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);

    }


    private void gallery()

    {

        Intent gallery = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery , 2);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    displayImage.setImageBitmap(photo);
                    //Uri selectedImage = imageReturnedIntent.getData();
                    // displayImage.setImageURI(selectedImage);
                    byte[] photobyte = getBytes(photo);
                    photobyteDB = photobyte;
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    displayImage.setImageURI(selectedImage);
                }
                break;
        }}


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
    public boolean onCreateOptionsMenu( Menu menu )
    {
        getMenuInflater().inflate( R.menu.add_menu, menu );
        return true;
    }



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
                startActivity(new Intent(this,ChooseLocation.class));
                return true;


            case R.id.action_add_image:
                //Toast.makeText(Update.this,"Data Saved For id"+id,Toast.LENGTH_SHORT).show();
                final CharSequence[] items = { "Take Photo", "Choose from Library",
                        "Cancel" };
                AlertDialog.Builder builder = new AlertDialog.Builder(Pop.this);
                builder.setTitle("Add Photo!");

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if (items[item].equals("Take Photo")) {
                            camera();
                        } else if (items[item].equals("Choose from Library")) {

                            gallery();
                        } else if (items[item].equals("Cancel")) {
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





