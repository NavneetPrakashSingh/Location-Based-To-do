package com.example.dell.to_dolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Pop extends Activity {

    //private static final String TAG = "Pop";


    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText editTxt;
    private ImageButton btn;
    private ImageButton btnCancel;
    private ImageButton submit;
    private ImageButton location;
    private ImageButton calender;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);


        editTxt = (EditText) findViewById(R.id.editText);
        btn = (ImageButton) findViewById(R.id.button);
        submit = (ImageButton) findViewById(R.id.submit);
        location = (ImageButton) findViewById(R.id.location);
        calender = (ImageButton) findViewById(R.id.calender);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice, arrayList);

        list.setAdapter(adapter);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;
        int height = metric.heightPixels;

        getWindow().setLayout((int) (width * 0.7), (int) (height * 0.7));

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



        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Pop.this,
                        android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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


    }
}






