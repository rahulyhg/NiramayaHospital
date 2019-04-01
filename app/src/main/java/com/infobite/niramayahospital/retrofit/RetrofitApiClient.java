package com.infobite.niramayahospital.retrofit;

import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.doctor.appointement.DoctorAppointementModel;
import com.infobite.niramayahospital.models.doctor.medicine_pathology.MedicinePathologyMainModal;

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

    @FormUrlEncoded
    @POST(Constant.DOCTOR_APPOINTMENT_STATUS)
    Call<ResponseBody> appointmentStatus(@Field("appointment_id") String appointment_id,
                                         @Field("appointment_status") String appointment_status);

    @FormUrlEncoded
    @POST(Constant.MEDICINE_PATHOLOGY)
    Call<MedicinePathologyMainModal> medicinePathology(@Field("hospital_id") String hospital_id);

    @FormUrlEncoded
    @POST(Constant.SUBMIT_PRESCRIPTION_PAITAINT)
    Call<ResponseBody> submitPaitaintPrescription(@Field("appointment_id") String appointment_id,
                                                  @Field("cheif_complaints") String cheif_complaints,
                                                  @Field("vitals") String vitals,
                                                  @Field("bp") String bp,
                                                  @Field("heart_rate_per_min") String heart_rate_per_min,
                                                  @Field("resp_rate_min") String resp_rate_min,
                                                  @Field("temp") String temp,
                                                  @Field("pain_score") String pain_score,
                                                  @Field("treatement_given") String treatement_given,
                                                  @Field("treatment_advice") String treatment_advice,
                                                  @Field("dr_id") String dr_id,
                                                  @Field("follow_up_advice") String follow_up_advice,
                                                  @Field("type_of_discharge") String type_of_discharge,
                                                  @Field("pathology_test_list") String pathology_test_list,
                                                  @Field("medicine_list") String medicine_list);

}