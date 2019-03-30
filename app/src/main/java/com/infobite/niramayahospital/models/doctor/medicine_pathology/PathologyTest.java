package com.infobite.niramayahospital.models.doctor.medicine_pathology;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PathologyTest implements Parcelable
{

@SerializedName("pathology_test_id")
@Expose
private String pathologyTestId;
@SerializedName("pathology_title")
@Expose
private String pathologyTitle;
public final static Parcelable.Creator<PathologyTest> CREATOR = new Creator<PathologyTest>() {


@SuppressWarnings({
"unchecked"
})
public PathologyTest createFromParcel(Parcel in) {
return new PathologyTest(in);
}

public PathologyTest[] newArray(int size) {
return (new PathologyTest[size]);
}

}
;

protected PathologyTest(Parcel in) {
this.pathologyTestId = ((String) in.readValue((String.class.getClassLoader())));
this.pathologyTitle = ((String) in.readValue((String.class.getClassLoader())));
}

public PathologyTest() {
}

public String getPathologyTestId() {
return pathologyTestId;
}

public void setPathologyTestId(String pathologyTestId) {
this.pathologyTestId = pathologyTestId;
}

public String getPathologyTitle() {
return pathologyTitle;
}

public void setPathologyTitle(String pathologyTitle) {
this.pathologyTitle = pathologyTitle;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(pathologyTestId);
dest.writeValue(pathologyTitle);
}

public int describeContents() {
return 0;
}

    @Override
    public String toString() {
        return pathologyTitle ;
    }
}