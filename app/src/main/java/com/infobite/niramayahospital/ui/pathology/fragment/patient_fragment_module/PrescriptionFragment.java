package com.infobite.niramayahospital.ui.pathology.fragment.patient_fragment_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.ui.pharmacy.activity.ViewPastPrescriptionActivity;
import com.infobite.niramayahospital.utils.BaseFragment;

public class PrescriptionFragment extends BaseFragment implements View.OnClickListener{
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.path_fragment_prescription, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {
        ((Button) rootView.findViewById(R.id.btnViewPastPrescription)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnViewPastPrescription:
                startActivity(new Intent(mContext, ViewPastPrescriptionActivity.class));
                break;
        }

    }

}
