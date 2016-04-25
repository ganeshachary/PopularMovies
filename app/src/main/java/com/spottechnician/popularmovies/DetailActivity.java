package com.spottechnician.popularmovies;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getResources().getConfiguration().orientation == Configuration.SCREENLAYOUT_SIZE_LARGE || getResources().getConfiguration().orientation == Configuration.SCREENLAYOUT_SIZE_XLARGE || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else {
            setSupportActionBar(toolbar);
        }





        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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
