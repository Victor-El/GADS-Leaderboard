package me.codeenzyme.gadsleaderboard.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TopSkilled implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<TopSkilled>() {

        @Override
        public TopSkilled createFromParcel(Parcel source) {
            return new TopSkilled(source);
        }

        @Override
        public TopSkilled[] newArray(int size) {
            return new TopSkilled[size];
        }
    };

    private String name;
    private int score;
    private String country;
    private String  badgeUrl;

    private TopSkilled(Parcel parcel) {
        this.name = parcel.readString();
        this.score = parcel.readInt();
        this.country = parcel.readString();
        this.badgeUrl = parcel.readString();
    }

    public TopSkilled(String name, int score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.score);
        dest.writeString(this.country);
        dest.writeString(this.badgeUrl);
    }
}
