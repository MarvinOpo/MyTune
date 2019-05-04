package com.mvopo.mytune.helper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mvopo.mytune.R;
import com.mvopo.mytune.models.Track;
import com.mvopo.mytune.views.MainActivity;
import com.mvopo.mytune.views.fragments.TrackDetailFragment;

import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.CardViewHolder> {

    private Context mContext;
    private ArrayList<Track> trackList;

    public TrackAdapter(Context mContext, ArrayList<Track> tracks) {
        this.mContext = mContext;
        this.trackList = tracks;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tune_item, viewGroup, false);
        CardViewHolder holder = new  CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int i) {
        holder.trackArtIv.layout(0,0,0,0);

        final Track track = trackList.get(i);

        //Load image from path using GLIDE
        Glide.with(mContext)
                .load(track.getTrackArt())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_photo)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.trackArtIv);

        holder.trackNameTv.setText(track.getTrackName());
        holder.trackGenreTv.setText(track.getTrackGenre());
        holder.trackPriceTv.setText("$" + track.getTrackPrice());

        holder.holderCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("track", track);

                TrackDetailFragment tdf = new TrackDetailFragment();
                tdf.setArguments(bundle);

                FragmentTransaction ft = ((MainActivity) mContext).getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .add(R.id.fragment_container, tdf)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        CardView holderCv;
        ImageView trackArtIv;
        TextView trackNameTv, trackGenreTv, trackPriceTv;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            holderCv = itemView.findViewById(R.id.item_holder);

            trackArtIv = itemView.findViewById(R.id.item_image);
            trackNameTv = itemView.findViewById(R.id.item_name);
            trackGenreTv = itemView.findViewById(R.id.item_genre);
            trackPriceTv = itemView.findViewById(R.id.item_price);
        }
    }
}
