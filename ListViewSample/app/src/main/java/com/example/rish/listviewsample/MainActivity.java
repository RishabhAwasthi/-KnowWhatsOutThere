package com.example.rish.listviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<MyListItems> listItems = new ArrayList<MyListItems>();
        MyListItems list1 = new MyListItems();
        list1.setItemImage(R.drawable.ic_launcher_foreground);
        list1.setItemName("Orange");
        list1.setItemQuantity("200");

        listItems.add(list1);

        MyListItems list2 = new MyListItems();
        list2.setItemQuantity("300");
        list2.setItemName("Mango");
        list2.setItemImage(R.drawable.ic_launcher_background);
        listItems.add(list2);

        MyListItems list3 = new MyListItems();
        list3.setItemQuantity("400");
        list3.setItemName("Man");
        list3.setItemImage(R.drawable.ic_launcher_background);
        listItems.add(list3);





        MyListAdapter adapter = new MyListAdapter(MainActivity.this,listItems);
        listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}
