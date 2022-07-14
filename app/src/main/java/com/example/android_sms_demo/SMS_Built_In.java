package com.example.android_sms_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SMS_Built_In extends AppCompatActivity {

    Button btnStartMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_built_in);

        btnStartMessage = (Button)findViewById(R.id.btnStartMessaging);

        btnStartMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });
    }

    @SuppressLint("IntentReset")
    protected void sendSMS(){
        Log.v("SendSMS","");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address","9375049355");
        smsIntent.putExtra("sms_body", "Hello My Friend, How are you doing today?");
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                    + "9375049355")));
            finish();
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "SMS Failed. Please Try Again Later!!!!", Toast.LENGTH_LONG).show();
        }
    }
}
