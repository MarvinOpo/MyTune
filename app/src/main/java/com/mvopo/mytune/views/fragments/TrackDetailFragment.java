package com.mvopo.mytune.views.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mvopo.mytune.R;
import com.mvopo.mytune.models.Track;

public class TrackDetailFragment extends Fragment {

    private CoordinatorLayout detailContainer;
    private TextView trackNameTv, trackGenreTv, trackPriceTv, trackDescTv;

    private Track track;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_detail, container, false);

        track = getArguments().getParcelable("track");

        detailContainer = view.findViewById(R.id.detail_container);
        trackNameTv = view.findViewById(R.id.detail_name);
        trackGenreTv = view.findViewById(R.id.detail_genre);
        trackPriceTv = view.findViewById(R.id.detail_price);
        trackDescTv = view.findViewById(R.id.detail_desc);

        setViewData();

        return view;
    }

    //Load data to their respective fields
    public void setViewData(){
        Glide.with(getContext())
                .load(track.getTrackArt())
                .error(R.drawable.no_photo)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        detailContainer.setBackground(resource);
                    }
                });

        trackNameTv.setText(track.getTrackName());
        trackGenreTv.setText(track.getTrackGenre());
        trackPriceTv.setText("$" + track.getTrackPrice());
        trackDescTv.setText(track.getDescription());
    }
}
