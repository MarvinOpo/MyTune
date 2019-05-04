package com.mvopo.mytune.contracts;

import com.mvopo.mytune.models.Track;
import com.mvopo.mytune.models.TrackDao;

import java.util.List;

public class HomeContract {

    public interface HomeView{
        boolean isListEmpty();
        TrackDao getTrackDao();

        void setAdapter(List<Track> trackList);

        void showTapRetry();
        void hideTapRetry();

        void showProgressBar();
        void hideProgressBar();

        void toastConnectionError();
    }

    public interface HomeAction{
        void getAllTracks();

        void saveTracks(List<Track> trackList);
        void getLocalTracks();
    }
}
