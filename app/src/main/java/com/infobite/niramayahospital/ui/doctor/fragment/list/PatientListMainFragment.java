package com.infobite.niramayahospital.ui.doctor.fragment.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.fragmentUtils;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSort;

public class PatientListMainFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    public static FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_patient_list, container, false);
        init(savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {

        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.GONE);

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);

        rootView.findViewById(R.id.tvAddPrescription).setOnClickListener(this);
        rootView.findViewById(R.id.tvVieweReports).setOnClickListener(this);
        rootView.findViewById(R.id.tvPastHistory).setOnClickListener(this);
        rootView.findViewById(R.id.tvNurseNotes).setOnClickListener(this);

        fragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            replaceFragment(new AddPrescriptionFragment(), Constant.AddPrescriptionFragment);
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        fragmentUtils.replaceFragment(fragment, Constant.PatientDetailFragment, R.id.framePatientListFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddPrescription:
                replaceFragment(new AddPrescriptionFragment(), Constant.AddPrescriptionFragment);
                ((TextView) rootView.findViewById(R.id.tvAddPrescription)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPastHistory)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvVieweReports)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvNurseNotes)).setBackgroundColor(getResources().getColor(R.color.gray_i));

                break;
            case R.id.tvVieweReports:
                replaceFragment(new ViewReportsFragment(), Constant.ViewReportsFragment);
                ((TextView) rootView.findViewById(R.id.tvVieweReports)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvAddPrescription)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvPastHistory)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvNurseNotes)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.tvPastHistory:
                replaceFragment(new PastHistoryFragment(), Constant.PastHistoryFragment);
                ((TextView) rootView.findViewById(R.id.tvPastHistory)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvNurseNotes)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvVieweReports)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvAddPrescription)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.tvNurseNotes:
                replaceFragment(new NurseNotesFragment(), Constant.NurseNotesFragment);
                ((TextView) rootView.findViewById(R.id.tvNurseNotes)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPastHistory)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvVieweReports)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvAddPrescription)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
        }
    }
}
