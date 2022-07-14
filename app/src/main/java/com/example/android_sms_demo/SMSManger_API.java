package com.example.android_sms_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSManger_API extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_SEND_SMS = 0;
    Button smsBtn;
    EditText mobileNumber, message;
    String mobile, mesg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_manger_api);

        smsBtn = (Button)findViewById(R.id.smsButton);
        mobileNumber = (EditText)findViewById(R.id.phoneNumberEditText);
        message = (EditText)findViewById(R.id.messageEditText);

        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(SMSManger_API.this, SMS_Built_In.class);
                startActivity(newIntent);
                sendMessage();
            }
        });
    }

    protected void sendMessage()
    {
        mobile = mobileNumber.getText().toString();
        mesg = message.getText().toString();

        if(ContextCompat.checkSelfPermission(SMSManger_API.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){

            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode)
        {
            case PERMISSION_REQUEST_SEND_SMS:{
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    SmsManager smsmanager = SmsManager.getDefault();
                    smsmanager.sendTextMessage(mobile, null, mesg, null, null);
                    Toast.makeText(this, "SMS Sent Successfully", Toast.LENGTH_LONG).show();
                }
                else{
;                    Toast.makeText(this,"SMS Failed Please Try Again Later", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}