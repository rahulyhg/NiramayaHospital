package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.utils.BaseFragment;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSort;


public class ProfileFragment extends BaseFragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgNotification.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
    }
}
