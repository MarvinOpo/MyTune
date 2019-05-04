package com.mvopo.mytune.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "track_tbl")
public class Track implements Parcelable {

    @SerializedName("trackId")
    @Id(autoincrement = false)
    private Long trackId;

    @SerializedName("artworkUrl100")
    private String trackArt;

    @SerializedName("trackName")
    private String trackName;

    @SerializedName("primaryGenreName")
    private String trackGenre;

    @SerializedName("trackPrice")
    private String trackPrice;

    @SerializedName("longDescription")
    private String description;

    @Generated(hash = 1080897940)
    public Track(Long trackId, String trackArt, String trackName, String trackGenre,
            String trackPrice, String description) {
        this.trackId = trackId;
        this.trackArt = trackArt;
        this.trackName = trackName;
        this.trackGenre = trackGenre;
        this.trackPrice = trackPrice;
        this.description = description;
    }

    @Generated(hash = 1672506944)
    public Track() {
    }

    protected Track(Parcel in) {
        if (in.readByte() == 0) {
            trackId = null;
        } else {
            trackId = in.readLong();
        }
        trackArt = in.readString();
        trackName = in.readString();
        trackGenre = in.readString();
        trackPrice = in.readString();
        description = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public Long getTrackId() {
        return this.trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getTrackArt() {
        return this.trackArt;
    }

    public void setTrackArt(String trackArt) {
        this.trackArt = trackArt;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackGenre() {
        return this.trackGenre;
    }

    public void setTrackGenre(String trackGenre) {
        this.trackGenre = trackGenre;
    }

    public String getTrackPrice() {
        return this.trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (trackId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(trackId);
        }
        parcel.writeString(trackArt);
        parcel.writeString(trackName);
        parcel.writeString(trackGenre);
        parcel.writeString(trackPrice);
        parcel.writeString(description);
    }
}
