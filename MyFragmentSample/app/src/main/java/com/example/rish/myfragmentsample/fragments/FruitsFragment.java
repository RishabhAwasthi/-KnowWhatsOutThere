package com.example.rish.myfragmentsample.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rish.myfragmentsample.ListItem;
import com.example.rish.myfragmentsample.MyListAdapter;
import com.example.rish.myfragmentsample.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FruitsFragment extends Fragment {


    public FruitsFragment() {
        // Required empty public constructor
    }
 private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.generic_fragment_layout, container, false);

        ArrayList<ListItem> listItems = new ArrayList<ListItem>();

        listItems.add(new ListItem("banana","4004",R.drawable.ic_launcher_background));
        listItems.add(new ListItem("berry","5001",R.drawable.ic_launcher_background));

        list = v.findViewById(R.id.list);
        final MyListAdapter adapter = new MyListAdapter(getActivity(),listItems);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItem value=adapter.getItem(position);
                Toast.makeText(getActivity(),value.getItemQuant(), Toast.LENGTH_SHORT).show();
               /* Intent i = new Intent(getActivity(),DrawerActivity.class);
                startActivity(i);*/
            }
        });



        return v;
    }

}
