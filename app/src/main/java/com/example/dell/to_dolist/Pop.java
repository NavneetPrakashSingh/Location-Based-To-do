package com.example.dell.to_dolist;

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

import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
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

public class Pop extends Activity {

    //private static final String TAG = "Pop";
    private static final String DATABASE_NAME = "todo_database";
    private AppDatabase appDatabase = Room.databaseBuilder(this,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();


    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private ImageButton btn;
    private ImageButton btnCancel;
    private ImageButton submit;
    private ImageButton location;
    private ImageButton calender;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private byte[] photobyteDB;
    private byte[] dbFetchedbyte;
    private Bitmap photofinal;
    private ProgressBar mprogress;

    private Button selectCamera;
    private ImageView displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));


        final EditText editTxt1 = findViewById(R.id.editText);
        btn = (ImageButton) findViewById(R.id.button);
        submit = (ImageButton) findViewById(R.id.submit);
        location = (ImageButton) findViewById(R.id.location);
        calender = (ImageButton) findViewById(R.id.calender);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        mprogress = (ProgressBar)findViewById(R.id.progress);
      //  mprogress.setVisibility(View.GONE);

        selectCamera = (Button) findViewById(R.id.selectCamera);
        displayImage = (ImageView) findViewById(R.id.displayImage);




        selectCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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

            }
        });


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);

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

                mprogress.setVisibility(View.VISIBLE);




                String value ="";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String titleValue = String.valueOf(editTxt1.getText());

                       Task task = new Task(titleValue,"Milk","1/2/3","1","2/2/3","1","1","1", photobyteDB);
                    appDatabase.taskModel().insertTaskWithId(task);


                  /*    for (int i=0;i<arrayList.size();i++){
                            Subtask sTask = new Subtask(String.valueOf(arrayList.get(i)),lastestTaskInserted.intValue(),0);
                            appDatabase.subTaskModel().insertSubTask(sTask);
                       }*/


                      //  dbFetchedbyte = appDatabase.taskModel().fetchTaskById(1).getImage();

                    //  photofinal = getImage(dbFetchedbyte);


                    }
                }).start();



               // mprogress.setVisibility(View.GONE);



               startActivity(new Intent(Pop.this,MainActivity.class));

               //displayImage.setImageBitmap(photofinal);

            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
               // mDisplayDate.setText(date);
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















}





