package com.infobite.niramayahospital.ui.doctor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.PatientListAdapter;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.ui.doctor.fragment.list.PatientListMainFragment;
import com.infobite.niramayahospital.ui.doctor.fragment.profile_details.ProfileMainFragment;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.fragmentUtils;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSort;

public class PatientListFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private RecyclerView rvPatientList;
    private List<String> patientLIst = new ArrayList<>();
    private PatientListAdapter patientListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_patientlist, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {

        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.GONE);

        rvPatientList = rootView.findViewById(R.id.rvPatientLIst);

        for (int i = 0; i <= 3; i++) {
            patientLIst.add("A");
            patientLIst.add("David Backham");
            patientLIst.add("Patient ID");
        }

        rvPatientList.setHasFixedSize(true);
        rvPatientList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        patientListAdapter = new PatientListAdapter(patientLIst, mContext, this);
        rvPatientList.setAdapter(patientListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llUserContent:
                Toast.makeText(mContext, "Aise hi...", Toast.LENGTH_SHORT).show();
                replaceFragment(new PatientListMainFragment(), Constant.PatientFragment);
                break;
        }
    }

    private void replaceFragment(Fragment fragment, String tag){
            fragmentUtils
                    .replaceFragment(fragment, Constant.PatientDetailFragment, R.id.home_frame);
    }
}
