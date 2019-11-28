package com.ceng319.testfragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO: Add the detailfragment into the layout.
        AddFragment();
    }

    public void AddFragment(){
        // TODO: 9: Needs to add fragment when it is not tablet landscape orientation.
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        DetailFragment fragment = new DetailFragment();

        // TODO: 10. Get the data passed from the mainactivity
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String position = extras.getString("position");  // passed key message
            //TODO: 11 Still Pass the data to detailfragment.
            Bundle fromDetail = new Bundle();
            fromDetail.putString("position", position);
            fragment.setArguments(fromDetail);
            fragmentManager.beginTransaction()
                    .replace(R.id.detailactivity, fragment, "Detail_Fragment")
                    .commit();
        }
    }
}
