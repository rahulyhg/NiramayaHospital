package com.infobite.niramayahospital.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.ui.activity.PrescriptionActivity;
import com.infobite.niramayahospital.ui.activity.UpcomingAppointmentActivity;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgSort;

public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private TextView tvUpcomingAppointment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init();
        return rootView;
    }

    private void init() {

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSearch :

                break;
            case R.id.tvUpcomingAppointment :
                startActivity(new Intent(mContext, UpcomingAppointmentActivity.class));
                break;

        }
    }
}
