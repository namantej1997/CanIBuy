package com.example.canibuy;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private String messageToScan="hello";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
//                Log.i("Text", messageText);
//                if(messageText.toLowerCase().contains("credit")) {
//                    Toast.makeText(SMSActivity.this, "hello: " + messageText+" by anu", Toast.LENGTH_LONG).show();
//                    getMoney(messageText);
//                }
            }
        });

    }


    public void getMoney(String messageText) {

        Log.i("Text", messageText);
        Toast.makeText(SMSActivity.this,"Done nin",Toast.LENGTH_LONG).show();
        String regex = "credited";

        Toast.makeText(SMSActivity.this,regex,Toast.LENGTH_LONG).show();
        String messageToSearch = messageText;
        int money = Integer.parseInt(messageToSearch.replaceAll("[^0-9]", ""));

        Toast.makeText(SMSActivity.this,""+money,Toast.LENGTH_LONG).show();

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(messageToSearch);
        Map<String, Integer> moneyleft = new HashMap<>();

        while (matcher.find()) {
            moneyleft.put(regex, money);
            break;

        }


        firebaseFirestore.collection("money").add(moneyleft);
    }
}