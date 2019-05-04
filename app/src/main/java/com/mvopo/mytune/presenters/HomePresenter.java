package com.mvopo.mytune.presenters;

import com.mvopo.mytune.contracts.HomeContract;
import com.mvopo.mytune.contracts.TrackApi;
import com.mvopo.mytune.models.RetrofitModel;
import com.mvopo.mytune.models.Track;
import com.mvopo.mytune.models.TrackDao;
import com.mvopo.mytune.models.TrackList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.HomeAction {

    private HomeContract.HomeView homeView;

    private TrackDao trackDao;

    public HomePresenter(HomeContract.HomeView homeView) {
        this.homeView = homeView;
        trackDao = homeView.getTrackDao();
    }

    @Override
    public void getAllTracks() {
        //Read local data for display
        if (homeView.isListEmpty()) getLocalTracks();

        // Invoke request from iTuneAPi
        TrackApi trackApi = RetrofitModel.getInstance().create(TrackApi.class);
        Call<TrackList> call = trackApi.getAllTracks();
        call.enqueue(new Callback<TrackList>() {
            @Override
            public void onResponse(Call<TrackList> call, Response<TrackList> response) {
                List<Track> trackList = response.body().getTrackList();
                homeView.setAdapter(trackList);

                //Call hide here so progress is still visible even local
                //data has already loaded
                homeView.hideProgressBar();

                //Save new fetched data to local DB
                saveTracks(trackList);
            }

            @Override
            public void onFailure(Call<TrackList> call, Throwable t) {
                //Show retry if there is no local data
                if (homeView.isListEmpty()) homeView.showTapRetry();
            }
        });
    }

    @Override
    public void saveTracks(List<Track> trackList) {

        int counter = 0;

        for (int i = 0; i < trackList.size(); i++) {
            Track track = trackList.get(i);

            /*
            Check ID to avoid duplication
            Set counter as ID
            If TRACK0 is remove from response and another track
            with no ID is added, it will replace TRACK0
            */
            if (track.getTrackId() == null) track.setTrackId((long) counter++);

            trackDao.insertOrReplace(track);
        }
    }

    @Override
    public void getLocalTracks() {
        //Loads All local track
        List<Track> trackList = trackDao.loadAll();

        if (!trackList.isEmpty()) {
            homeView.setAdapter(trackList);
        }
    }
}
