package com.infobite.niramayahospital.ui.doctor.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.AppointmentDetailsAdapter;
import com.infobite.niramayahospital.adapter.AppointmentTimeSlotsAdapter;
import com.infobite.niramayahospital.models.doctor.appointement.AppointementDatum;
import com.infobite.niramayahospital.models.doctor.appointement.DoctorAppointementModel;
import com.infobite.niramayahospital.models.doctor.appointement.OpdAppointment;
import com.infobite.niramayahospital.models.user.UserModel;
import com.infobite.niramayahospital.retrofit.RetrofitApiClient;
import com.infobite.niramayahospital.retrofit.RetrofitService;
import com.infobite.niramayahospital.retrofit.WebResponse;
import com.infobite.niramayahospital.ui.doctor.activity.UpcomingAppointmentActivity;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import java.util.ArrayList;

import retrofit2.Response;

import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSort;

public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView tvUpcomingAppointment;
    private RecyclerView rvTimeSlots, rvAppointments;
    private ArrayList<AppointementDatum> appointementDataList;
    private AppointmentTimeSlotsAdapter slotsAdapter;
    private AppointmentDetailsAdapter detailsAdapter;
    public RetrofitApiClient retrofitApiClient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init();
        return rootView;
    }

    private void init() {
        retrofitApiClient = RetrofitService.getRetrofit();
        tvUpcomingAppointment = rootView.findViewById(R.id.tvUpcomingAppointment);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.GONE);
        imgSearch.setVisibility(View.VISIBLE);
        imgSort.setVisibility(View.GONE);

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        imgSearch.setOnClickListener(this);
        tvUpcomingAppointment.setOnClickListener(this);

        rvTimeSlots = rootView.findViewById(R.id.rvTimeSlots);
        rvAppointments = rootView.findViewById(R.id.rvAppointments);

        fetchAppointmentData();
    }

    private void fetchAppointmentData() {

        String doctorId = UserModel.getUserModel().getUser().getId();

        if (cd.isNetworkAvailable()) {
            RetrofitService.getDoctorAppointment(new Dialog(mContext), retrofitApiClient.doctorAppointment(doctorId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {

                    DoctorAppointementModel appointmentModel = (DoctorAppointementModel) result.body();

                    if (!appointmentModel.getError()){
                        if (appointmentModel.getData()!=null && appointmentModel.getData().size()>0){
                            appointementDataList = (ArrayList<AppointementDatum>) appointmentModel.getData();
                            slotsAdapter = new AppointmentTimeSlotsAdapter(appointementDataList, mContext, DashboardFragment.this);
                            rvTimeSlots.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                            rvTimeSlots.setAdapter(slotsAdapter);
                            slotsAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Log.d("NIRAMAYA HOSPITAL", error);
                }
            });
            /*startActivity(new Intent(mContext, BypassActivity.class));*/
        }

       /* RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        prescriptionAdapter = new AppointmentTimeSlotsAdapter(prescriptionList, mContext);
        rvMedicine.setLayoutManager(layoutManager);
        rvMedicine.setAdapter(prescriptionAdapter);
        prescriptionAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSearch :
                break;
            case R.id.tvUpcomingAppointment :
                startActivity(new Intent(mContext, UpcomingAppointmentActivity.class));
                break;
            case R.id.rlAvailable :
                int tag = (int) v.getTag();
                initAppointmentDetail(appointementDataList.get(tag));
                break;
        }
    }

    private void initAppointmentDetail(AppointementDatum appointementDatum) {
        detailsAdapter = new AppointmentDetailsAdapter((ArrayList<OpdAppointment>) appointementDatum.getOpdAppointment(), mContext, DashboardFragment.this);
        rvAppointments.setLayoutManager(new LinearLayoutManager(mContext));
        rvAppointments.setAdapter(detailsAdapter);
        detailsAdapter.notifyDataSetChanged();
    }
}
