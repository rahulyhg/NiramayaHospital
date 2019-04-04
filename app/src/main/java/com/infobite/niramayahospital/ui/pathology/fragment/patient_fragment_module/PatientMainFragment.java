package com.infobite.niramayahospital.ui.pathology.fragment.patient_fragment_module;

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

import static com.infobite.niramayahospital.ui.pathology.activity.MainActivity.fragmentUtilsHome;

public class PatientMainFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    public static FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.path_fragment_main_patient, container, false);
        init(savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {

    /*    imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.GONE);*/

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);

        rootView.findViewById(R.id.tvPrescription).setOnClickListener(this);
        rootView.findViewById(R.id.tvViewReports).setOnClickListener(this);
        rootView.findViewById(R.id.tvContact).setOnClickListener(this);


        fragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            replaceFragment(new ContactFragment(), Constant.ContactFragment);
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        fragmentUtilsHome.replaceFragment(fragment, Constant.PatientDetailFragment, R.id.framePatientListFragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvContact:
                replaceFragment(new ContactFragment(), Constant.ContactFragment);
                ((TextView) rootView.findViewById(R.id.tvContact)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPrescription)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvViewReports)).setBackgroundColor(getResources().getColor(R.color.gray_i));

                break;
            case R.id.tvPrescription:
                replaceFragment(new PrescriptionFragment(), Constant.PrescriptionFragment);
                ((TextView) rootView.findViewById(R.id.tvPrescription)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvContact)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvViewReports)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.tvViewReports:
                replaceFragment(new ReportsFragment(), Constant.ViewReportsFragment);
                ((TextView) rootView.findViewById(R.id.tvViewReports)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPrescription)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvContact)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
        }
    }
}
