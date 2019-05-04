package com.mvopo.mytune.contracts;

import com.mvopo.mytune.models.TrackList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrackApi {

    @GET("/search?term=star&amp;country=au&amp;media=movie")
    Call<TrackList> getAllTracks();
}
