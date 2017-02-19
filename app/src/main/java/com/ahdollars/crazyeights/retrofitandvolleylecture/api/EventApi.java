package com.ahdollars.crazyeights.retrofitandvolleylecture.api;

import com.ahdollars.crazyeights.retrofitandvolleylecture.model.Event;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Shashank on 02-10-2016.
 */

public interface EventApi {

    //convention
    //when we know that we will get only 1 output use path format
    //else use query parameter


    //first set the path through annotation
    @GET("events")
    Call<ArrayList<Event>> listEvents();


    //{id} is a path

    @GET("events/{id}/event")
    Call<Event> getEventDetails(@Path("id") int id);
    //here getEvent(10)  will be equvalent to /event/10/event  to get the appropriate event




}
