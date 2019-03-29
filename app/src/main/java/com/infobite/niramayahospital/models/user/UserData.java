package com.infobite.niramayahospital.models.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("session_key")
    @Expose
    private String sessionKey;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_contact")
    @Expose
    private String userContact;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_house_number")
    @Expose
    private String userHouseNumber;
    @SerializedName("user_street")
    @Expose
    private String userStreet;
    @SerializedName("user_city")
    @Expose
    private String userCity;
    @SerializedName("user_state")
    @Expose
    private String userState;
    @SerializedName("user_country")
    @Expose
    private String userCountry;
    @SerializedName("user_profile_picture")
    @Expose
    private String userProfilePicture;
    @SerializedName("user_gender")
    @Expose
    private String userGender;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_date_of_birth")
    @Expose
    private String userDateOfBirth;
    @SerializedName("user_registration_date")
    @Expose
    private String userRegistrationDate;
    public final static Parcelable.Creator<UserData> CREATOR = new Creator<UserData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return (new UserData[size]);
        }

    };

    protected UserData(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.sessionKey = ((String) in.readValue((String.class.getClassLoader())));
        this.userEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.userContact = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.userHouseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.userStreet = ((String) in.readValue((String.class.getClassLoader())));
        this.userCity = ((String) in.readValue((String.class.getClassLoader())));
        this.userState = ((String) in.readValue((String.class.getClassLoader())));
        this.userCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.userProfilePicture = ((String) in.readValue((String.class.getClassLoader())));
        this.userGender = ((String) in.readValue((String.class.getClassLoader())));
        this.userType = ((String) in.readValue((String.class.getClassLoader())));
        this.userDateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.userRegistrationDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserData() {
    }

    public static UserData userData;

    public static UserData getUserData() {
        return userData;
    }

    public static void setUserData(UserData userData) {
        UserData.userData = userData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHouseNumber() {
        return userHouseNumber;
    }

    public void setUserHouseNumber(String userHouseNumber) {
        this.userHouseNumber = userHouseNumber;
    }

    public String getUserStreet() {
        return userStreet;
    }

    public void setUserStreet(String userStreet) {
        this.userStreet = userStreet;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserRegistrationDate() {
        return userRegistrationDate;
    }

    public void setUserRegistrationDate(String userRegistrationDate) {
        this.userRegistrationDate = userRegistrationDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(sessionKey);
        dest.writeValue(userEmail);
        dest.writeValue(userContact);
        dest.writeValue(userName);
        dest.writeValue(userHouseNumber);
        dest.writeValue(userStreet);
        dest.writeValue(userCity);
        dest.writeValue(userState);
        dest.writeValue(userCountry);
        dest.writeValue(userProfilePicture);
        dest.writeValue(userGender);
        dest.writeValue(userType);
        dest.writeValue(userDateOfBirth);
        dest.writeValue(userRegistrationDate);
    }

    public int describeContents() {
        return 0;
    }

}