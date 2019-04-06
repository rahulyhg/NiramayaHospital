package com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectCategoryMainModal implements Parcelable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("medcinie")
    @Expose
    private List<Medcinie> medcinie = new ArrayList<Medcinie>();
    public final static Parcelable.Creator<SelectCategoryMainModal> CREATOR = new Creator<SelectCategoryMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SelectCategoryMainModal createFromParcel(Parcel in) {
            return new SelectCategoryMainModal(in);
        }

        public SelectCategoryMainModal[] newArray(int size) {
            return (new SelectCategoryMainModal[size]);
        }

    };

    protected SelectCategoryMainModal(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.medcinie, (com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.Medcinie.class.getClassLoader()));
    }

    public SelectCategoryMainModal() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Medcinie> getMedcinie() {
        return medcinie;
    }

    public void setMedcinie(List<Medcinie> medcinie) {
        this.medcinie = medcinie;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(medcinie);
    }

    public int describeContents() {
        return 0;
    }

}