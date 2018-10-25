package com.gadgetscure.gadgetscure.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gadgetscure.gadgetscure.R;

import java.util.Objects;


public class ReceiptActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.Bookingtoolbar);
        toolbar.setTitle("Booking Summary");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        View v = findViewById(R.id.UserId);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        assert extras != null;
        String userid = extras.getString("UserId");
        String message = extras.getString("Message");
        TextView userId = (TextView) findViewById(R.id.UserId);
        userId.setText(userid);
        TextView message1 = (TextView) findViewById(R.id.message);
        message1.setText(message);



    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(ReceiptActivity.this,MainActivity.class);

        startActivity(startMain);

    }



}
