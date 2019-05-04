package com.mvopo.mytune.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mvopo.mytune.DBApp;
import com.mvopo.mytune.R;
import com.mvopo.mytune.contracts.HomeContract;
import com.mvopo.mytune.helper.TrackAdapter;
import com.mvopo.mytune.models.Track;
import com.mvopo.mytune.models.TrackDao;
import com.mvopo.mytune.presenters.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.HomeView {

    private HomePresenter presenter;

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private TrackAdapter adapter;

    private ArrayList<Track> trackList = new ArrayList<>();

    private TextView tapRetryTv;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        presenter = new HomePresenter(this);

        progressBar = view.findViewById(R.id.progress_bar);
        tapRetryTv = view.findViewById(R.id.home_retry);

        recyclerView = view.findViewById(R.id.home_recycler);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        presenter.getAllTracks();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showProgressBar();
        presenter.getAllTracks();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean isListEmpty() {
        return trackList.isEmpty();
    }

    @Override
    public TrackDao getTrackDao() {
        //Return instance of DAO for DB manipulation
        DBApp dbApp = (DBApp) getActivity().getApplicationContext();
        TrackDao trackDao = dbApp.getDaoSession().getTrackDao();
        return trackDao;
    }

    @Override
    public void setAdapter(List<Track> trackList) {
        this.trackList.clear();
        this.trackList.addAll(trackList);

        adapter = new TrackAdapter(getContext(), this.trackList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showTapRetry() {
        tapRetryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressBar();
                hideTapRetry();
                presenter.getAllTracks();
            }
        });

        tapRetryTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTapRetry() {
        //Remove event for request retry
        tapRetryTv.setOnClickListener(null);
        tapRetryTv.setVisibility(View.GONE);

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void toastConnectionError() {
        Toast.makeText(getContext(), R.string.conn_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
