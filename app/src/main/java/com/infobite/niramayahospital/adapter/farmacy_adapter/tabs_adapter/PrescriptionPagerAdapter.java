package com.infobite.niramayahospital.adapter.farmacy_adapter.tabs_adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.infobite.niramayahospital.ui.pharmacy.fragment.prescription_tabs.CreateInvoiceTabFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.prescription_tabs.DoseChartTabFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.prescription_tabs.PreviewInvoiceTabFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.prescription_tabs.ViewPrescriptionFragment;

public class PrescriptionPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 4;

    public PrescriptionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new ViewPrescriptionFragment();
                break;
            case 1:
                fragment = new CreateInvoiceTabFragment();
                break;
            case 2:
                fragment = new PreviewInvoiceTabFragment();
                break;
                case 3:
                fragment = new DoseChartTabFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "View Prescription";
                break;
            case 1:
                title = "Create Invoice";
                break;
            case 2:
                title = "Preview Invoice";
                break;
                case 3:
                title = "Dose Chart";
                break;
        }
        return title;
    }

}
