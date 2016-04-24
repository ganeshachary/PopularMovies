package com.spottechnician.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by OnesTech on 23/04/2016.
 */
public class ListReviewsAdapter extends BaseAdapter {
    Context context;
    List<String> values;
    LayoutInflater inflater = null;

    ListReviewsAdapter(Context context, List<String> values) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.values = values;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View MyConvertView = convertView;
        if (MyConvertView == null) {
            MyConvertView = inflater.inflate(R.layout.review_list, null);
            //holder.textView1=(TextView)MyConvertView.findViewById(R.id.reviewauthor);
            holder.textView2 = (TextView) MyConvertView.findViewById(R.id.reviewcontent);
            holder.textView2.setText(values.get(position));


        } else {
            MyConvertView = convertView;
        }
        return convertView;
    }

    class Holder {
        TextView textView1, textView2;
    }
}
