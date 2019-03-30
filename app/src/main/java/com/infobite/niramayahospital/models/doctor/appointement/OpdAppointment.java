package com.infobite.niramayahospital.models.doctor.appointement;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpdAppointment implements Parcelable {

    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("appointment_type")
    @Expose
    private String appointmentType;
    @SerializedName("patient")
    @Expose
    private String patient;
    @SerializedName("appointment_payment_status")
    @Expose
    private String appointmentPaymentStatus;
    @SerializedName("appointment_date")
    @Expose
    private String appointmentDate;
    public final static Parcelable.Creator<OpdAppointment> CREATOR = new Creator<OpdAppointment>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OpdAppointment createFromParcel(Parcel in) {
            return new OpdAppointment(in);
        }

        public OpdAppointment[] newArray(int size) {
            return (new OpdAppointment[size]);
        }

    };

    protected OpdAppointment(Parcel in) {
        this.appointmentId = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentType = ((String) in.readValue((String.class.getClassLoader())));
        this.patient = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentPaymentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OpdAppointment() {
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getAppointmentPaymentStatus() {
        return appointmentPaymentStatus;
    }

    public void setAppointmentPaymentStatus(String appointmentPaymentStatus) {
        this.appointmentPaymentStatus = appointmentPaymentStatus;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appointmentId);
        dest.writeValue(appointmentType);
        dest.writeValue(patient);
        dest.writeValue(appointmentPaymentStatus);
        dest.writeValue(appointmentDate);
    }

    public int describeContents() {
        return 0;
    }

}