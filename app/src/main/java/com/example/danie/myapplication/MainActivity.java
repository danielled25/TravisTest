package com.example.danie.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //ID code
    static final int CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //===Part 1: Encrypt and decrypt the sentence===
        //Button that deals with the click
        final Button encrypt = findViewById(R.id.button);
        //Text field for user information
        et = findViewById(R.id.editText);

        //What happens when encrypt button is clicked
        encrypt.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //Get the text entered into the text field
                Editable e = et.getText();
                //Send it to a method
                startEncryptActivity(e);

            }
        });

    private void startEncryptActivity(Editable e){
        //Create an intent to the second activity
        Intent in = new Intent(MainActivity.this, SecondActivity.class);
        //Pass the text field contents into the intent
        in.putExtra("dataInEditText", e.toString());
        //Start the second activity and send the intent
        startActivity(in);
    }

    
 }