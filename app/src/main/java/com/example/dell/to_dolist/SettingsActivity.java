package com.example.dell.to_dolist;

import android.app.Activity;
import android.content.Intent;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        /*ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable( Color.parseColor("#3F51B5")));*/

        feedback=(TextView)findViewById( R.id.message_title ) ;
        submit_Feedback=(Button) findViewById( R.id.submitFeedback );
        final Pattern pattern =  Pattern.compile(email_pattern);
        //setupActionBar();

        submit_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
										 

            public void onClick(View v) {
                errorFlag=false;
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

    public void sendFeedback()
    {
        submit_Feedback=findViewById( R.id.submitFeedback );
        Toast.makeText( this, "submitFeedback "+submit_Feedback.getText(), Toast.LENGTH_SHORT ).show();
    }
    
    }



