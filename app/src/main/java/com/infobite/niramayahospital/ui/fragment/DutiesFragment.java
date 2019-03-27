package com.infobite.niramayahospital.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.activity.HomeActivity.imgSort;

public class DutiesFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_schedule, container, false);
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
    }

    @Override
    public void onClick(View v) {

    }
}
