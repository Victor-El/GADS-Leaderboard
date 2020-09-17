package me.codeenzyme.gadsleaderboard.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TopLearner implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator<TopLearner>() {
        @Override
        public TopLearner createFromParcel(Parcel source) {
            return new TopLearner(source);
        }

        @Override
        public TopLearner[] newArray(int size) {
            return new TopLearner[size];
        }
    };

    private String name;
    private int hours;
    private String country;
    private String badgeUrl;

    public TopLearner(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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

    public void setUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public TopLearner(Parcel parcel) {
        name = parcel.readString();
        hours = parcel.readInt();
        country = parcel.readString();
        badgeUrl = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.hours);
        dest.writeString(this.country);
        dest.writeString(this.badgeUrl);
    }
}
