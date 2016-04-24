package com.spottechnician.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.spottechnician.popularmovies.data.MovieContract;
import com.spottechnician.popularmovies.data.MovieDbHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.MenuItemCompat.getActionProvider;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    final static String baseurlformovie = "http://api.themoviedb.org/3/movie/";
    String iddb, titledb, overviewdb, datedb, votedb, posterpathdb, reviewurldb, trailorurldb, links = "";
    String[] jsonarry = new String[2];
    String baseurl = "http://image.tmdb.org/t/p/w342/";
    List<String> listtrailorcode = new ArrayList<String>();
    ArrayList<String> listreviewlist = new ArrayList<String>();
    //HashMap<String,String> listreviewlist=new HashMap<String,String>();
    ImageView imageView;
    FetchMovieDetails fetchMovieDetails;
    TextView overviewtext, releasedatetext, ratingtext, titletext;
    ListView trailorlistview, reviewslistview;
    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle extras = null;
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            extras = intent.getExtras();
        }

        String id = extras.getString("id");
        iddb = id;
        String posterpath = extras.getString("poster_path").substring(1);
        posterpathdb = posterpath;
        String overview = extras.getString("overview");
        overviewdb = overview;

        String release_date = extras.getString("release_date");
        datedb = release_date;
        String original_title = extras.getString("original_title");
        titledb = original_title;

        String vote_average = extras.getString("vote_average");
        votedb = vote_average;
        String finalurl = baseurl + posterpath;
        posterpathdb = finalurl;


        fetchMovieDetails = new FetchMovieDetails();
        fetchMovieDetails.execute(id);
        // List view for trailor and reviews
        // reviewslistview=(ListView)rootView.findViewById(R.id.reviewlistview);
        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,listreviewlist);
        //reviewslistview.setAdapter(arrayAdapter);
        trailorlistview = (ListView) rootView.findViewById(R.id.trailorlistview);
        //ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,listtrailorcode);
        //trailorlistview.setAdapter(arrayAdapter2);
        trailorlistview.setAdapter(new ListTrailorAdapter(getContext(), listtrailorcode));

        trailorlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = (String) parent.getAdapter().getItem(position);
                String url = "http://www.youtube.com/watch?v=" + key;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

            }
        });

        imageView = (ImageView) rootView.findViewById(R.id.back_drop_image);
        overviewtext = (TextView) rootView.findViewById(R.id.description);
        overviewtext.setText(overview);
        releasedatetext = (TextView) rootView.findViewById(R.id.releasedate);
        releasedatetext.setText(release_date);
        ratingtext = (TextView) rootView.findViewById(R.id.rating);
        ratingtext.setText(vote_average + "/10");
        titletext = (TextView) rootView.findViewById(R.id.movietitle);
        titletext.setText(original_title);


        Button reviewbtn = (Button) rootView.findViewById(R.id.reviewbtn);
        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent MyIntent = new Intent(getContext(), Reviews.class);
                MyIntent.putStringArrayListExtra("values", listreviewlist);
                startActivity(MyIntent);

            }
        });


        //adding favorite movies
        Button favbtn = (Button) rootView.findViewById(R.id.favoritebtn);
        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMovieToDatabase();
            }
        });

        // imageView.setImageResource(Thumnails[position]);
        Picasso.with(getActivity()).load(finalurl).into(imageView);

        return rootView;
    }


    public void addMovieToDatabase() {
        MovieDbHelper movieDbHelper = new MovieDbHelper(getContext());

        MovieContract movieContract = new MovieContract(iddb, titledb, overviewdb, datedb,
                votedb, posterpathdb, reviewurldb, trailorurldb);
        if (movieDbHelper.addMovie(movieContract)) {
            Toast.makeText(getContext(), "Added to favorite", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Already in Favorite", Toast.LENGTH_SHORT).show();

        }


    }

    public void parseTrailor(String jsonstring) {
        JSONObject jsonObjectTrailor;
        JSONArray jsonArrayTrailor;
        try {
            jsonObjectTrailor = new JSONObject(jsonstring);
            links = "";
            jsonArrayTrailor = jsonObjectTrailor.getJSONArray("results");
            listtrailorcode.clear();
            for (int i = 0; i < jsonArrayTrailor.length(); i++) {
                JSONObject jsonObject = jsonArrayTrailor.getJSONObject(i);
                String key = jsonObject.getString("key");
                listtrailorcode.add(key);

                links = links + "\n" + "http://www.youtube.com/watch?v=" + listtrailorcode.get(i);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("MovieTrailor", listtrailorcode.get(0));

    }

    public void parseReview(String jsonstring) {
        JSONObject jsonObjectReview;
        JSONArray jsonArrayReview;
        try {
            jsonObjectReview = new JSONObject(jsonstring);

            jsonArrayReview = jsonObjectReview.getJSONArray("results");
            listreviewlist.clear();
            for (int i = 0; i < jsonArrayReview.length(); i++) {
                JSONObject jsonObject = jsonArrayReview.getJSONObject(i);
                String content = jsonObject.getString("content");
                String author = jsonObject.getString("author");
                listreviewlist.add(content);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) getActionProvider(menuItem);
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareTrailorIntent());
        } else {
            Log.d("Share button", "Share Action Provider is null?");
        }
    }


    private Intent createShareTrailorIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        if (links != null) {
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    titledb + links);
        } else {
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    titledb);
        }

        return shareIntent;
    }

    class FetchMovieDetails extends AsyncTask<String, Void, String[]> {
        ProgressDialog progress;
        BufferedReader bufferedReader = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(getContext());
            progress.setMessage("Downloading");
            progress.show();
        }

        @Override
        protected String[] doInBackground(String... params) {
            String id = params[0];
            String trailorurl = baseurlformovie + params[0] + "/videos?api_key=" + BuildConfig.MOVIEDB_API;
            String reviewurl = baseurlformovie + params[0] + "/reviews?api_key=" + BuildConfig.MOVIEDB_API;
            HttpURLConnection httpURLConnection = null;
            HttpURLConnection httpURLConnection2 = null;
            StringBuffer stringBuffer1 = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            String line, jsontrailor = "nodata", jsonreview;
            try {
                URL url1 = new URL(trailorurl);
                URL url2 = new URL(reviewurl);
                httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.connect();
                InputStream inputStream1 = httpURLConnection.getInputStream();
                InputStream inputStream2 = httpURLConnection2.getInputStream();

                if (inputStream1 == null) {
                    return null;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream1));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer1.append(line);
                }
                jsonarry[0] = stringBuffer1.toString();

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream2));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer2.append(line);
                }
                jsonarry[1] = stringBuffer2.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return jsonarry;
        }

        @Override
        protected void onPostExecute(String... jsonarray) {
            //Log.e("MovieTrailor",jsonarray[0]);
            //Log.e("MovieReviews",jsonarray[1]);
            parseTrailor(jsonarray[0]);
            parseReview(jsonarray[1]);
            progress.dismiss();
        }
    }
}
