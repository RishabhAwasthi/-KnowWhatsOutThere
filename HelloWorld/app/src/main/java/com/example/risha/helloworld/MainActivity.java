package com.example.risha.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt= findViewById(R.id.txt1);
        txt.setText("Rishabh");
        txt=findViewById(R.id.txt2);
        txt.setText("Adil");



    }
}
