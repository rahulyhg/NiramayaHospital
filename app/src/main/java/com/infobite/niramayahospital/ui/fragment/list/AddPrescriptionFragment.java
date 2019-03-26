package com.infobite.niramayahospital.ui.fragment.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.ui.activity.PrescriptionActivity;
import com.infobite.niramayahospital.utils.BaseFragment;

public class AddPrescriptionFragment extends BaseFragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_prescription, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews();

    }

    private void initViews() {
        //startActivity(new Intent(mContext, PrescriptionActivity.class));
        ((Button)rootView.findViewById(R.id.btn_done)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PrescriptionActivity.class));
            }
        });
    }
}
