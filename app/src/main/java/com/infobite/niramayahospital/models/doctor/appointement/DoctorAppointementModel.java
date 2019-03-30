package com.infobite.niramayahospital.models.doctor.appointement;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorAppointementModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<AppointementDatum> data = null;
    public final static Parcelable.Creator<DoctorAppointementModel> CREATOR = new Creator<DoctorAppointementModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DoctorAppointementModel createFromParcel(Parcel in) {
            return new DoctorAppointementModel(in);
        }

        public DoctorAppointementModel[] newArray(int size) {
            return (new DoctorAppointementModel[size]);
        }

    }
            ;

    protected DoctorAppointementModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (AppointementDatum.class.getClassLoader()));
    }

    public DoctorAppointementModel() {
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

    public List<AppointementDatum> getData() {
        return data;
    }

    public void setData(List<AppointementDatum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}