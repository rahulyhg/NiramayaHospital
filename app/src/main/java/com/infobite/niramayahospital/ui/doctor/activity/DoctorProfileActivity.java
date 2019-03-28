package com.infobite.niramayahospital.ui.doctor.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.DoctorReviewRatingAdapter;
import com.infobite.niramayahospital.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorProfileActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView rvdoctorAppointment;
    private DoctorReviewRatingAdapter reviewRatingAdapter;
    private List<String> reviewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        rvdoctorAppointment = findViewById(R.id.rvdoctorAppointment);

        for (int i=0; i<1; i++) {
            reviewList.add("Mohit Rana ");
            reviewList.add("8 hours ago");
            reviewList.add("The doctor is very good with exellent skill.");
        }

        rvdoctorAppointment.setHasFixedSize(true);
        rvdoctorAppointment.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        reviewRatingAdapter = new DoctorReviewRatingAdapter(mContext, reviewList);
        rvdoctorAppointment.setAdapter(reviewRatingAdapter);
        reviewRatingAdapter.notifyDataSetChanged();
        rvdoctorAppointment.scrollToPosition(0);
        onclickListner();
    }

    private void onclickListner() {
        ((ImageView)findViewById(R.id.icBackDoctor)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icBackDoctor:
                finish();
                break;
        }
    }
}
