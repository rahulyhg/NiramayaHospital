package com.infobite.niramayahospital.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private UserData user;
    public final static Parcelable.Creator<UserModel> CREATOR = new Creator<UserModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        public UserModel[] newArray(int size) {
            return (new UserModel[size]);
        }

    }
            ;

    protected UserModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((UserData) in.readValue((UserData.class.getClassLoader())));
    }

    public UserModel() {
    }

    public static UserModel getUserModel() {
        return userModel;
    }

    public static void setUserModel(UserModel userModel) {
        UserModel.userModel = userModel;
    }

    public static UserModel userModel;

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

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(user);
    }

    public int describeContents() {
        return 0;
    }

}