package com.example.rish.knowwhatsoutthere;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListAdapter extends ArrayAdapter<HashMap<String,String>> {

    private Context context;
    private ArrayList<HashMap<String, String>> data;

    public MyListAdapter(Activity context, ArrayList<HashMap<String, String>> list) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViewsw, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, list );
        this.context=context;
        this.data=list;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = view;


        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_row, parent, false);
        }

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);


        TextView textView =(TextView) listItemView.findViewById(R.id.author);
        textView.setText(song.get(MainActivity.KEY_AUTHOR));

        TextView textView1 =(TextView) listItemView.findViewById(R.id.title);
        textView1.setText(song.get(MainActivity.KEY_TITLE));

        TextView sdetails = (TextView) listItemView.findViewById(R.id.sdetails);
        TextView time = (TextView) listItemView.findViewById(R.id.time);
        time.setText(song.get(MainActivity.KEY_PUBLISHEDAT));
        sdetails.setText(song.get(MainActivity.KEY_DESCRIPTION));

        ImageView imageview = (ImageView) listItemView.findViewById(R.id.galleryImage);



        if(song.get(MainActivity.KEY_URLTOIMAGE).toString().length() < 5)
        {
            imageview.setVisibility(View.GONE);
        }else{
            Picasso.with(context)
                    .load(song.get(MainActivity.KEY_URLTOIMAGE).toString())
                    .resize(300, 200)
                    .into(imageview);
        }




        return listItemView;
    }
}
