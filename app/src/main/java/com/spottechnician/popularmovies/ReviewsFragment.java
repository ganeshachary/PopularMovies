package com.spottechnician.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReviewsFragment extends Fragment {
    ArrayList<String> values;
    ListView listView;

    public ReviewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent i = getActivity().getIntent();
        values = i.getStringArrayListExtra("values");

        if (values.isEmpty()) {
            values.add("No Reviews Yet");
        }
        View rootview = inflater.inflate(R.layout.fragment_reviews, container, false);
        listView = (ListView) rootview.findViewById(R.id.listreviewactivity);

        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, values));


        return rootview;
    }
}
