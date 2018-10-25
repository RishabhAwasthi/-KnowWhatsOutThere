package com.example.rish.fragmentseg;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class FruitsFragment extends Fragment {

    public FruitsFragment() {
        // Required empty public constructor
    }
ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fruits, container, false);

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
        listItems.add(new ListItem("banana","4004",R.drawable.ic_launcher_background));

        list = v.findViewById(R.id.list);
        final MyListAdapter adapter = new MyListAdapter(getActivity(),listItems);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem value=adapter.getItem(position);
                Toast.makeText(getActivity(),value.getItemQuant(), Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }


}
