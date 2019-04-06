package com.infobite.niramayahospital.ui.doctor.fragment.profile_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.ProfilePagerAdapter;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.fragmentUtils;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgEditProfile;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.doctor.activity.HomeActivity.imgSort;

public class ProfileMainFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private ViewPager viewPager;
    private ProfilePagerAdapter profilePagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile_main, container, false);
        init(savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {

       /* imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgEditProfile.setVisibility(View.VISIBLE);*/

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        imgSearch.setOnClickListener(this);
        imgEditProfile.setOnClickListener(this);

        rootView.findViewById(R.id.tvContactDetails).setOnClickListener(this);
        rootView.findViewById(R.id.tvPersonalDetails).setOnClickListener(this);
        rootView.findViewById(R.id.tvServiceDetails).setOnClickListener(this);
        rootView.findViewById(R.id.llDoctorProfile).setOnClickListener(this);

        if (savedInstanceState == null) {
            replaceFragment(new ContactFragment(), Constant.ContactFragment);
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        fragmentUtils.replaceFragment(fragment, Constant.ProfileMainFragment, R.id.frameProfileFragment);

        //fragmentUtils.replaceFragment(new DashboardFragment(), Constant.DashboardFragment, R.id.home_frame);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvContactDetails:
                replaceFragment(new ContactFragment(), Constant.ContactFragment);
                ((TextView) rootView.findViewById(R.id.tvContactDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPersonalDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvServiceDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));

                break;
            case R.id.tvPersonalDetails:
                replaceFragment(new PersonalDetailFragment(), Constant.PersonalDetailFragment);
                ((TextView) rootView.findViewById(R.id.tvPersonalDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvContactDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvServiceDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.tvServiceDetails:
                replaceFragment(new ServiceDetailFragment(), Constant.ServiceDetailFragment);
                ((TextView) rootView.findViewById(R.id.tvServiceDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPersonalDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvContactDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.imgEditProfile:
                replaceFragment(new ContactEditFragment(), Constant.ContactEditFragment);
                ((TextView) rootView.findViewById(R.id.tvContactDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView) rootView.findViewById(R.id.tvPersonalDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView) rootView.findViewById(R.id.tvServiceDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.llDoctorProfile:
                //    startActivity(new Intent(mContext, DoctorProfileActivity.class));
                break;
        }
    }
}
