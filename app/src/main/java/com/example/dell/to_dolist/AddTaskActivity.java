/*
* This block of code was taken from
 * https://github.com/delaroy/RoomPersistentLibrary/tree/master/app/src/main/java/com/delaroystudios/roomdatabaseview
* */

package com.example.dell.to_dolist;


/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity for entering a word.
 */

public class AddTaskActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    private  EditText edit_task_title, edit_task_content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        edit_task_title = findViewById(R.id.edit_task_title);
        edit_task_content = findViewById(R.id.edit_task_desc);



        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(edit_task_title.getText()) || TextUtils.isEmpty(edit_task_content.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String task_title = edit_task_title.getText().toString();
                    String task_content = edit_task_content.getText().toString();


                    replyIntent.putExtra(TITLE, task_title);
                    replyIntent.putExtra(CONTENT, task_content);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}



