package com.infobite.niramayahospital.models.doctor.appointement;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointementDatum implements Parcelable
{

@SerializedName("opd_schedule_id")
@Expose
private String opdScheduleId;
@SerializedName("opd_title")
@Expose
private String opdTitle;
@SerializedName("opd_start_time")
@Expose
private String opdStartTime;
@SerializedName("opd_end_time")
@Expose
private String opdEndTime;
@SerializedName("opd_appointment")
@Expose
private List<OpdAppointment> opdAppointment = null;
public final static Parcelable.Creator<AppointementDatum> CREATOR = new Creator<AppointementDatum>() {


@SuppressWarnings({
"unchecked"
})
public AppointementDatum createFromParcel(Parcel in) {
return new AppointementDatum(in);
}

public AppointementDatum[] newArray(int size) {
return (new AppointementDatum[size]);
}

}
;

protected AppointementDatum(Parcel in) {
this.opdScheduleId = ((String) in.readValue((String.class.getClassLoader())));
this.opdTitle = ((String) in.readValue((String.class.getClassLoader())));
this.opdStartTime = ((String) in.readValue((String.class.getClassLoader())));
this.opdEndTime = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.opdAppointment, (com.infobite.niramayahospital.models.doctor.appointement.OpdAppointment.class.getClassLoader()));
}

public AppointementDatum() {
}

public String getOpdScheduleId() {
return opdScheduleId;
}

public void setOpdScheduleId(String opdScheduleId) {
this.opdScheduleId = opdScheduleId;
}

public String getOpdTitle() {
return opdTitle;
}

public void setOpdTitle(String opdTitle) {
this.opdTitle = opdTitle;
}

public String getOpdStartTime() {
return opdStartTime;
}

public void setOpdStartTime(String opdStartTime) {
this.opdStartTime = opdStartTime;
}

public String getOpdEndTime() {
return opdEndTime;
}

public void setOpdEndTime(String opdEndTime) {
this.opdEndTime = opdEndTime;
}

public List<OpdAppointment> getOpdAppointment() {
return opdAppointment;
}

public void setOpdAppointment(List<OpdAppointment> opdAppointment) {
this.opdAppointment = opdAppointment;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(opdScheduleId);
dest.writeValue(opdTitle);
dest.writeValue(opdStartTime);
dest.writeValue(opdEndTime);
dest.writeList(opdAppointment);
}

public int describeContents() {
return 0;
}

}