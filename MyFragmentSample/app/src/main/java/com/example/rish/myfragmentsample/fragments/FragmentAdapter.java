package com.example.rish.myfragmentsample.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    private int page_count=2;

    private String tabTitles[] = new String[]{"Fruits","Vegetables"};

    public FragmentAdapter(FragmentManager fm, Context context){

        super(fm);
        this.context =context;
    }


    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new FruitsFragment();
        else
            return new VegetablesFragment();


    }

    @Override
    public int getCount() {

        return page_count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];

    }
}
