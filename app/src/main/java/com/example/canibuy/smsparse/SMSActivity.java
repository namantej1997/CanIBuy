package com.example.canibuy.smsparse;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.canibuy.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Todo : BroadcastReceiver Class To Receive And Read SMS

public class SMSActivity extends AppCompatActivity {

    public static final String MONEY_REGEX = "[0-9]{1,6}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {

                //From the received text string you may do string operations to get the required OTP
                //It depends on your SMS format
                Log.e("Message",messageText);
                Toast.makeText(SMSActivity.this,"Message: "+messageText,Toast.LENGTH_LONG).show();

                // If your OTP is six digits number, you may use the below code

                Pattern pattern = Pattern.compile(MONEY_REGEX);
                Matcher matcher = pattern.matcher(messageText);
                String money = "";
                while (matcher.find())
                {
                    money = matcher.group();
                }

                Toast.makeText(SMSActivity.this,"OTP: "+ money ,Toast.LENGTH_LONG).show();

            }
        });
    }
}