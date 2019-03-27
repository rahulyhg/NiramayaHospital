package com.infobite.niramayahospital.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.PatientListAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class PatientListFragment extends BaseFragment {
    private View rootView;
    private RecyclerView rvPatientList;
    private List<String> patientLIst = new ArrayList<>();
    private PatientListAdapter patientListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_patientlist, container, false);
        mContext  = getActivity();
        init();
        return rootView;
    }

    private void init() {
        rvPatientList = rootView.findViewById(R.id.rvPatientLIst);

        for (int i=0; i<=3; i++){
            patientLIst.add("A");
            patientLIst.add("David Backham");
            patientLIst.add("Patient ID");
        }

        rvPatientList.setHasFixedSize(true);
        rvPatientList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        patientListAdapter = new PatientListAdapter(patientLIst, mContext);
        rvPatientList.setAdapter(patientListAdapter);
    }
}
