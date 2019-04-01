package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.ui.pharmacy.fragment.dashboard_fragment.LatestExpensesFragment;
import com.infobite.niramayahospital.ui.pharmacy.fragment.dashboard_fragment.LatestMedicineFragment;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.fragmentUtilsHome;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSort;


public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fr_fragment_dashboard, container, false);
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.VISIBLE);
        imgNotification.setVisibility(View.VISIBLE);
        imgSort.setVisibility(View.GONE);

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);

        rootView.findViewById(R.id.txtLatestSales).setOnClickListener(this);
        rootView.findViewById(R.id.txtLatestExpense).setOnClickListener(this);
        rootView.findViewById(R.id.txtLatestMedicine).setOnClickListener(this);
        rootView.findViewById(R.id.txtSalesGraph).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtLatestSales:
                fragmentUtilsHome.replaceFragment(new LatestSalesFragment(), Constant.LatestSalesFragment, R.id.home_frame);
                break;
            case R.id.txtLatestExpense:
                fragmentUtilsHome.replaceFragment(new LatestExpensesFragment(), Constant.LatestExpensesFragment, R.id.home_frame);
                break;
            case R.id.txtLatestMedicine:
                fragmentUtilsHome.replaceFragment(new LatestMedicineFragment(), Constant.LatestMedicineFragment, R.id.home_frame);
                break;
            case R.id.txtSalesGraph:
                break;
        }
    }
}
