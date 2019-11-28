package com.ceng319.testfragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class DetailFragment extends Fragment {
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: 7 Inflate the layout for this fragment
        // Inflater is used to get fragments into the XML layout.
        View view =  inflater.inflate(R.layout.detail_fragment, container, false);
        Bundle mArgs = this.getArguments();
        String countryId = mArgs.getString("position");
        if (mArgs !=null)
        {
            TextView mTextView = view.findViewById(R.id.detailPage);
            mTextView.setText("You clicked: " + SampleData.dataItemList.get(Integer.parseInt(countryId)).getItemName());
            ImageView mImageView = view.findViewById(R.id.detailImage);
            InputStream inputStream = null;
            try {
                String imageFile = SampleData.dataItemList.get(Integer.parseInt(countryId)).getImage();
                // File operations, need to get the image from asset files.
                inputStream = getContext().getAssets().open(imageFile);
                Drawable d = Drawable.createFromStream(inputStream, null);
                mImageView.setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return view;
    }

}
