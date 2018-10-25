package com.example.rish.listvieweg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ListItem> listItems = new ArrayList<ListItem>();
        ListItem list1 = new ListItem();
        list1.setItemQuant("20001");
        list1.setItemName("Oranges");
        list1.setItemImage(R.drawable.ic_launcher_background);
        listItems.add(list1);
        ListItem list2 = new ListItem();
        list2.setItemQuant("30001");
        list2.setItemName("Mangoes");
        list2.setItemImage(R.drawable.ic_launcher_foreground);
        listItems.add(list2);

        list = findViewById(R.id.list);
        final MyListAdapter adapter = new MyListAdapter(this,listItems);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem value=adapter.getItem(position);
                Toast.makeText(MainActivity.this,value.getItemQuant(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
