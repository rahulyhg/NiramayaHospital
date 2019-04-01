package com.infobite.niramayahospital.ui.pharmacy.fragment.dashboard_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.utils.BaseFragment;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.txtTitle;

public class LatestMedicineFragment extends BaseFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_latest_medicine, container, false);
        init();
        return rootView;
    }

    private void init() {
        txtTitle.setText("Latest Medicine");
    }
}
