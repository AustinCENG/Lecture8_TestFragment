package com.ceng319.testfragment;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {
    private boolean mIsTablet;
    // TODO 2: Define a List as ArrayList Collection Finished
    List<DataItem> dataList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the screen size of the device
        getScreenSize();

        // Get the framelayout to hold the fragment
        FrameLayout fragmentContainer =
                (FrameLayout) findViewById(R.id.detail_fragment_container);
        mIsTablet = (fragmentContainer != null);

        MainFragment mainFragment = new MainFragment();
        //TODO: 3 Add the mainactivity fragment to fragment1. This is done for both Tablet and Phone
        // remove the old one and add the new one.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.country_list_framelayout, mainFragment, "Main_Fragment")
                .commit();


    }

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.i("MapleLeaf", "Height: - " + height+ " Width: - " + width);
    }

    public boolean ismIsTablet() {
        return mIsTablet;
    }

    public void setmIsTablet(boolean mIsTablet) {
        this.mIsTablet = mIsTablet;
    }

    @Override
    public void sendFragmentData(Bundle data) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(data);
        //Replace the detail_fragment the data to detail_fragment.
        // remove the old one and add the new one.
        fragmentManager.beginTransaction()
                .replace(R.id.detail_fragment_container, fragment, "Detail_Fragment")
                .commit();
    }

}