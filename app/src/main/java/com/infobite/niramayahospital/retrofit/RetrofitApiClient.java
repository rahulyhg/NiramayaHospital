package com.infobite.niramayahospital.retrofit;

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
    @POST("login.php")
    Call<ResponseBody> login(@Field("uName") String uName,
                             @Field("uPass") String uPass);

}