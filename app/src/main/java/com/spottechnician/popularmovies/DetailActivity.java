package com.spottechnician.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    String baseurl="http://image.tmdb.org/t/p/w342/";
    ImageView imageView;
    TextView overviewtext,releasedatetext,ratingtext,titletext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        String posterpath=extras.getString("backdrop_path").substring(1);
        String overview=extras.getString("overview");
        String release_date=extras.getString("release_date");
        String original_title=extras.getString("original_title");
        String vote_average=extras.getString("vote_average");
        String finalurl=baseurl+posterpath;
        imageView=(ImageView)findViewById(R.id.back_drop_image);
        overviewtext=(TextView)findViewById(R.id.description);
        overviewtext.setText(overview);
        releasedatetext=(TextView)findViewById(R.id.releasedate);
        releasedatetext.setText(release_date);
        ratingtext=(TextView)findViewById(R.id.rating);
        ratingtext.setText(vote_average);
        titletext=(TextView)findViewById(R.id.movietitle);
        titletext.setText(original_title);
        // imageView.setImageResource(Thumnails[position]);
        Picasso.with(this).load(finalurl).into(imageView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    class PutImage extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
