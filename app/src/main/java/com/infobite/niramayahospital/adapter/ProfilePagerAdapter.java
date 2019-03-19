package com.infobite.niramayahospital.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.infobite.niramayahospital.ui.fragment.profile_details.ContactFragment;
import com.infobite.niramayahospital.ui.fragment.profile_details.PersonalDetailFragment;
import com.infobite.niramayahospital.ui.fragment.profile_details.ServiceDetailFragment;

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    private int COUNT = 3;

    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new ContactFragment();
                break;
            case 1:
                fragment = new PersonalDetailFragment();
                break;
            case 2:
                fragment = new ServiceDetailFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
