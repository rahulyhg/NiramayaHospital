package com.infobite.niramayahospital.models.doctor.medicine_pathology;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicinePathologyMainModal implements Parcelable
{

@SerializedName("error")
@Expose
private Boolean error;
@SerializedName("message")
@Expose
private String message;
@SerializedName("pathology_test")
@Expose
private List<PathologyTest> pathologyTest = new ArrayList<PathologyTest>();
@SerializedName("medciinie")
@Expose
private List<Medciinie> medciinie = new ArrayList<Medciinie>();
public final static Parcelable.Creator<MedicinePathologyMainModal> CREATOR = new Creator<MedicinePathologyMainModal>() {


@SuppressWarnings({
"unchecked"
})
public MedicinePathologyMainModal createFromParcel(Parcel in) {
return new MedicinePathologyMainModal(in);
}

public MedicinePathologyMainModal[] newArray(int size) {
return (new MedicinePathologyMainModal[size]);
}

}
;

protected MedicinePathologyMainModal(Parcel in) {
this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.pathologyTest, (com.infobite.niramayahospital.models.doctor.medicine_pathology.PathologyTest.class.getClassLoader()));
in.readList(this.medciinie, (com.infobite.niramayahospital.models.doctor.medicine_pathology.Medciinie.class.getClassLoader()));
}

public MedicinePathologyMainModal() {
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

public List<PathologyTest> getPathologyTest() {
return pathologyTest;
}

public void setPathologyTest(List<PathologyTest> pathologyTest) {
this.pathologyTest = pathologyTest;
}

public List<Medciinie> getMedciinie() {
return medciinie;
}

public void setMedciinie(List<Medciinie> medciinie) {
this.medciinie = medciinie;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(error);
dest.writeValue(message);
dest.writeList(pathologyTest);
dest.writeList(medciinie);
}

public int describeContents() {
return 0;
}

}