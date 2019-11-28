package com.ceng319.testfragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.List;

public class MainFragment extends Fragment {
    private FragmentListener mListener;

    public interface FragmentListener{
        public void sendFragmentData(Bundle data);
    }
    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Step 2: Define the Listener in the onAttach method.
        mListener = (FragmentListener) context;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO: 7 Inflate the layout for this fragment
        // Inflater is used to get fragments into the XML layout.
        View rootView =  inflater.inflate(R.layout.main_fragment, container, false);
        buildDataSet(rootView);


        return rootView;
    }



    private void buildDataSet(View rootView) {
        List<DataItem> dataItemList = SampleData.dataItemList;

        // TODO 4: Assign the list adapter and listview. Finished
         DataAdapter adapter = new DataAdapter(getActivity(), R.id.countryListView, dataItemList);

        ListView listView = (ListView) rootView.findViewById(R.id.countryListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MapleLeaf", Integer.toString(position));

                if (getActivity().getLocalClassName().compareTo("MainActivity") == 0)
                {
                    if (((MainActivity) getActivity()).ismIsTablet()){ // if tablet in landscape
                        Bundle mPosition = new Bundle();
                        mPosition.putString("position", Integer.toString(position));
                        mListener.sendFragmentData(mPosition);
                    }
                    else {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("position", Integer.toString(position));
                        startActivity(intent);
                    }
                }

            }
        });

        PrepareData();

    }

    private void PrepareData() {
        try {
            AssetManager assetManager = getActivity().getApplicationContext().getResources().getAssets();
            String[] files = assetManager.list("North_America");
            Log.d("MapleLeaf", files.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
