package com.infobite.niramayahospital.retrofit;

import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.doctor.appointement.DoctorAppointementModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitApiClient {

    @FormUrlEncoded
    @POST(Constant.USER_LOGIN)
    Call<ResponseBody> login(@Field("username") String username,
                             @Field("password") String password,
                             @Field("device_id") String device_id,
                             @Field("hospital_id") String hospital_id);

    @FormUrlEncoded
    @POST(Constant.DOCTOR_APPOINTMENT)
    Call<DoctorAppointementModel> doctorAppointment(@Field("doctor_id") String doctor_id);

}