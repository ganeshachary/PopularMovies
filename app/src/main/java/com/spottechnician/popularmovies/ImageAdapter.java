package com.spottechnician.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by onestech on 06/03/16.
 */
public class ImageAdapter extends BaseAdapter {

    Context context;
    String baseurl="http://image.tmdb.org/t/p/w342/";
    ArrayList<MovieModel> movieModelList;
    HashMap<String,HashMap<String,String>> moviehmap;
    private int Thumnails[]={R.drawable.ic_deadpool,
                                R.drawable.ic_deadpool,
                                R.drawable.ic_deadpool,
                                R.drawable.ic_deadpool,
                                R.drawable.ic_deadpool,
                                R.drawable.ic_deadpool,
                                R.drawable.ic_deadpool};
    public ImageAdapter(Context context)
    {

        this.context=context;
    }
    public ImageAdapter(Context context,HashMap<String,HashMap<String,String>> hmap)
    {

        this.context=context;
        moviehmap=hmap;
    }
    public ImageAdapter(Context context,ArrayList<MovieModel> movieModelList)
    {

        this.context=context;
        this.movieModelList=movieModelList;
    }

    @Override
    public int getCount() {
        return movieModelList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView==null)
        {
            imageView=new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        }
        else
        {
            imageView=(ImageView)convertView;
        }
        MovieModel m=movieModelList.get(position);
        String posterpath=m.getPosterpath().substring(1);
        String finalurl=baseurl+posterpath;
        Picasso.with(context).load(finalurl).into(imageView);

        return imageView;
    }


}
