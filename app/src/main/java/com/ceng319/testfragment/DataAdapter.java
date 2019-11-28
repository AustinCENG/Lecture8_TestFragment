package com.ceng319.testfragment;

// TODO 3: Create the arrayadapter to hold the data finished

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataAdapter extends ArrayAdapter {

    List<DataItem> mDataItems;
    LayoutInflater mInflater;


    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a layout to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public DataAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mDataItems = objects;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.countryName);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.flagView);

        DataItem item = mDataItems.get(position);

        tvName.setText(item.getItemName());


        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            // File operations, need to get the image from asset files.
            inputStream = getContext().getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
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
        return convertView;
    }
}
