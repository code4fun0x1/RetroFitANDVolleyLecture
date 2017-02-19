package com.ahdollars.crazyeights.retrofitandvolleylecture.model;

/**
 * Created by Shashank on 02-10-2016.
 */

public class Event {
    public String name,start_time,end_time,location_name;

    public Event(String end_time, String location_name, String name, String start_time) {
        this.end_time = end_time;
        this.location_name = location_name;
        this.name = name;
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
