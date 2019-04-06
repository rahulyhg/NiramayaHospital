package com.infobite.niramayahospital.models.doctor.medicine_pathology;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medciinie implements Parcelable {

    @SerializedName("medicine_id")
    @Expose
    private String medicineId;
    @SerializedName("medicine_title")
    @Expose
    private String medicineTitle;
    public final static Parcelable.Creator<Medciinie> CREATOR = new Creator<Medciinie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Medciinie createFromParcel(Parcel in) {
            return new Medciinie(in);
        }

        public Medciinie[] newArray(int size) {
            return (new Medciinie[size]);
        }

    };

    protected Medciinie(Parcel in) {
        this.medicineId = ((String) in.readValue((String.class.getClassLoader())));
        this.medicineTitle = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Medciinie() {
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineTitle() {
        return medicineTitle;
    }

    public void setMedicineTitle(String medicineTitle) {
        this.medicineTitle = medicineTitle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(medicineId);
        dest.writeValue(medicineTitle);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return medicineTitle;
    }
}