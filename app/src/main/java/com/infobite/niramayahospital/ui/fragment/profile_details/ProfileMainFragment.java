package com.infobite.niramayahospital.ui.fragment.profile_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.ProfilePagerAdapter;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.utils.BaseFragment;

public class ProfileMainFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private ViewPager viewPager;
    private ProfilePagerAdapter profilePagerAdapter;
    public static FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile_main,container,false);
        init(savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
  /*   viewPager = rootView.findViewById(R.id.viewPagerProfileFragment);
     if (viewPager != null){
        profilePagerAdapter = new ProfilePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(profilePagerAdapter);*/

        rootView.findViewById(R.id.tvContactDetails).setOnClickListener(this);
        rootView.findViewById(R.id.tvPersonalDetails).setOnClickListener(this);
        rootView.findViewById(R.id.tvServiceDetails).setOnClickListener(this);

        fragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            replaceFragment(new ContactFragment(), Constant.ContactFragment);
        }
    }
    private void replaceFragment(Fragment fragment,String tag){
        fragmentManager.beginTransaction()
                .replace(R.id.frameProfileFragment, fragment, tag)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvContactDetails:
                replaceFragment(new ContactFragment(), Constant.ContactFragment);
                ((TextView)rootView.findViewById(R.id.tvContactDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView)rootView.findViewById(R.id.tvPersonalDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView)rootView.findViewById(R.id.tvServiceDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));

                break;
            case R.id.tvPersonalDetails:
                replaceFragment(new PersonalDetailFragment(), Constant.PersonalDetailFragment);
                ((TextView)rootView.findViewById(R.id.tvPersonalDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView)rootView.findViewById(R.id.tvContactDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView)rootView.findViewById(R.id.tvServiceDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
            case R.id.tvServiceDetails:
                replaceFragment(new ServiceDetailFragment(), Constant.ServiceDetailFragment);
                ((TextView)rootView.findViewById(R.id.tvServiceDetails)).setBackground(getResources().getDrawable(R.drawable.layout_bg_gray_p6));
                ((TextView)rootView.findViewById(R.id.tvPersonalDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                ((TextView)rootView.findViewById(R.id.tvContactDetails)).setBackgroundColor(getResources().getColor(R.color.gray_i));
                break;
        }
    }
}
