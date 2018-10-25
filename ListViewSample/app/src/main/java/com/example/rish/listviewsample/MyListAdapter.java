package com.example.rish.listviewsample;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends ArrayAdapter<MyListItems> {

    public MyListAdapter(@NonNull Activity context, @NonNull ArrayList<MyListItems> objects)
    {
        super(context,0, objects);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row, parent, false);
        }

        final MyListItems listItem = getItem(position);

        ImageView imageview = (ImageView) listItemView.findViewById(R.id.img);
        imageview.setImageResource(listItem.getItemImage());

        TextView textView =(TextView) listItemView.findViewById(R.id.name);
        textView.setText(listItem.getItemName());

        TextView textView1 =(TextView) listItemView.findViewById(R.id.quantity);
        textView1.setText(listItem.getItemQuantity());


        return listItemView;
    }

}
