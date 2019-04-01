package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.tabs_adapter.PrescriptionPagerAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSort;


public class PrescriptionsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private ViewPager viewPager;
    private TabLayout tab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_prescription, container, false);
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.VISIBLE);
        imgNotification.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);
        setViewPager();
    }

    private void setViewPager() {
        viewPager = rootView.findViewById(R.id.viewPager);
        tab = rootView.findViewById(R.id.tabs);
        if (viewPager != null) {
            PrescriptionPagerAdapter adapter = new PrescriptionPagerAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
            tab.setupWithViewPager(viewPager);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
