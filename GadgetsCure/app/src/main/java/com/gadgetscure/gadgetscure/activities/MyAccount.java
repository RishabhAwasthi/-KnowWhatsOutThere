package com.gadgetscure.gadgetscure.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gadgetscure.gadgetscure.R;
import com.gadgetscure.gadgetscure.data.ImageSaver;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class MyAccount extends AppCompatActivity {

    private TextView pro_name,mail;
    private TextView phone,address;
    private static String musername= MainActivity.getMyString();
    private static String memail=MainActivity.getEmail();
    private static String mphone;
    private static String maddress;
    private static final int RC_PHOTO_PICKER = 2;
    private ImageView propic;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.acctoolbar);
        toolbar.setTitle("My Account");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        pro_name=(TextView)findViewById(R.id.acname);
        pro_name.setText(musername);
        mail=(TextView)findViewById(R.id.gmail);
        mail.setText(memail);
        phone=(TextView) findViewById(R.id.acphone);
        address=(TextView) findViewById(R.id.acadd);
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyAccount.this);
        mphone=sp.getString("Phone","");
        maddress=sp.getString("Address","");

        phone.setText(mphone);
        address.setText(maddress);

        propic=(ImageView) findViewById(R.id.dp);
        Bitmap bitmap = new ImageSaver(MyAccount.this).
                setFileName("myImage.png").
                setDirectoryName("images").
                load();
        if(bitmap!=null)
            propic.setImageBitmap(bitmap);
        TextView showbook=(TextView)findViewById(R.id.showbook);
        showbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccount.this, OrderActivity.class);
                startActivity(i);
            }
        });
        TextView pick=(TextView)findViewById(R.id.picker);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

            }
        });

        ImageView addphone=(ImageView)findViewById(R.id.addphone);
        ImageView addloc=(ImageView)findViewById(R.id.addloc);
        addphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog delte_dialog = new Dialog(MyAccount.this);
                delte_dialog.setContentView(R.layout.custom_dialog);
                final EditText input=(EditText) delte_dialog.findViewById(R.id.des);
                input.setInputType(InputType.TYPE_CLASS_PHONE);
                TextView title=(TextView)delte_dialog.findViewById(R.id.sure);
                title.setText("Update Phone No");

                delte_dialog.show();

                Button cancel = (Button) delte_dialog.findViewById(R.id.no);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delte_dialog.dismiss();
                    }
                });

                Button proceed = (Button) delte_dialog.findViewById(R.id.add);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mphone=input.getText().toString();
                        if(mphone.length()==10) {
                            phone.setText(mphone);

                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("Phone", mphone);
                            editor.commit();
                        }
                        else{
                            Toast.makeText(MyAccount.this,"Enter a valid Number",Toast.LENGTH_SHORT).show();
                        }




                        delte_dialog.dismiss();
                    }
                });
            }
        });
        addloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog delte_dialog = new Dialog(MyAccount.this);
                delte_dialog.setContentView(R.layout.custom_dialog);
                final  EditText input=(EditText) delte_dialog.findViewById(R.id.des);
                TextView title=(TextView)delte_dialog.findViewById(R.id.sure);
                title.setText("Update Address");


                delte_dialog.show();

                Button cancel = (Button) delte_dialog.findViewById(R.id.no);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delte_dialog.dismiss();
                    }
                });

                Button proceed = (Button) delte_dialog.findViewById(R.id.add);
                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        maddress=input.getText().toString();


                        address.setText(maddress);

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("Address",maddress);
                        editor.commit();




                        delte_dialog.dismiss();
                    }
                });

            }
        });





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK)
        {

            InputStream stream;
            try {
                Toast.makeText(MyAccount.this, "Image saved", Toast.LENGTH_SHORT).show();
                stream = MyAccount.this.getContentResolver().openInputStream(data.getData());
                Bitmap realImage = BitmapFactory.decodeStream(stream);
                new ImageSaver(MyAccount.this).
                        setFileName("myImage.png").
                        setDirectoryName("images").
                        save(realImage);
                // saveToInternalStorage(realImage);

                propic.setImageBitmap(realImage);
                Toast.makeText(MyAccount.this, "Profile Pic Updated", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MyAccount.this, MainActivity.class);
                startActivity(i);






            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

    }

}

