/* Class file for Feedback form, will be called from MainActvity.java */
package com.example.dell.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;


public class SettingsActivity extends Activity {

    Button submit_Feedback;
    EditText email;
    EditText message;
    TextView feedback;
    boolean errorFlag =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.feedback);
        String email_pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        feedback=(TextView)findViewById( R.id.message_title ) ;
        submit_Feedback=(Button) findViewById( R.id.submitFeedback );
        final Pattern pattern =  Pattern.compile(email_pattern);

        submit_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
										 

            public void onClick(View v) {
                errorFlag=false;
                // error handling for email input and text message
                email = (EditText) findViewById( R.id.email );
                if(TextUtils.isEmpty(  email.getText().toString().trim()))
                {
                    email.setError( "Invalid email" );
                }

                if(!(pattern.matcher( email.getText().toString().trim() ).matches()))
                {
                    email.setError( "Invalid email" );
                    errorFlag=true;
                }
                else
                    email.setError( null );

                message=(EditText)findViewById( R.id.feedbackText ) ;
                Toast.makeText( SettingsActivity.this,"message.getText().toString().trim() "+message.getText().toString().trim()+"-",Toast.LENGTH_LONG );
                if(message.getText().toString().trim().length()==0)
                {
                    feedback.setError( "Please enter your message" );
                    errorFlag=true;
                }
                else
                    feedback.setError( null );

                if(!errorFlag)
                {
                    Toast.makeText( SettingsActivity.this, "Thank you for your feedback", Toast.LENGTH_LONG ).show();
                    startActivity( new Intent( SettingsActivity.this,MainActivity.class ) );
                }

            }
        });
    }


    }



