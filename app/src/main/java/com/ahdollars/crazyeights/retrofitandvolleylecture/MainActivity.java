package com.ahdollars.crazyeights.retrofitandvolleylecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ahdollars.crazyeights.retrofitandvolleylecture.api.EventApi;
import com.ahdollars.crazyeights.retrofitandvolleylecture.model.Event;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static final String TAG="BATMAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //volley for Json,String,Bitmap
        //RetroFit for json ONLY with REST API
        //Piccaso for Images

        // frist create a request queue at the beginning of the activity
        //it uses a singleton pattern which is shared among the activities
        RequestQueue rQueue= Volley.newRequestQueue(this);
        //make a request
        String url="http://open-event-dev.herokuapp.com/api/v2/events";
        //if we know the format of the JSON array or object the directly use helper request class
        //but we will frst make string request
   /*     StringRequest rq=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );  */
        JsonArrayRequest rq=new JsonArrayRequest(url,  //no POST/GET method
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: "+System.currentTimeMillis());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //LruCache for ram based cache
        //Cache c=new DiskBasedCache(getCacheDir(),1024*1024);

        rq.setTag("EVENT");  //we can fetch this request and cancel it
        rQueue.cancelAll("EVENT");  //cancel all event with tag 'EVENT'
        rQueue.add(rq);

        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://open-event-dev.herokuapp.com/api/v2/")
                .build();  //check for slashes at end of the url and at beginning of the annotation
        EventApi eventApi=retrofit.create(EventApi.class);//create an implementatio of the interface

        //enqueue is async method
        eventApi.listEvents().enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, retrofit2.Response<ArrayList<Event>> response) {
                Log.d(TAG, "MAGIC KAITO: "+response.body().get(0).name);
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {

            }
        });

    }
}
