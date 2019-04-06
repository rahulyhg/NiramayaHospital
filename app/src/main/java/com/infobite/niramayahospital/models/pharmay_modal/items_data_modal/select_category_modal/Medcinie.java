package com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medcinie implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    public final static Parcelable.Creator<Medcinie> CREATOR = new Creator<Medcinie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Medcinie createFromParcel(Parcel in) {
            return new Medcinie(in);
        }

        public Medcinie[] newArray(int size) {
            return (new Medcinie[size]);
        }

    };

    protected Medcinie(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Medcinie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return title;
    }

}