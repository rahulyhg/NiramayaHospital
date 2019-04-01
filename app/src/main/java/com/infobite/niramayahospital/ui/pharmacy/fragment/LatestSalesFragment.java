package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.LatestSalesAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.txtTitle;

public class LatestSalesFragment extends BaseFragment {

    private View rootview;
    private LatestSalesAdapter latestSalesAdapter;
    private List<String> latestSalesList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_latest_sales, container, false);
        init();
        return rootview;
    }

    private void init() {
        txtTitle.setText("Latest Sales");
        RecyclerView rvLatestSales = rootview.findViewById(R.id.rvLatestSales);

        for (int i = 0; i <= 6; i++) {
            latestSalesList.add("6/03/09");
            latestSalesList.add("1599");
        }

        rvLatestSales.setHasFixedSize(true);
        rvLatestSales.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        latestSalesAdapter = new LatestSalesAdapter(latestSalesList, mContext);
        rvLatestSales.setAdapter(latestSalesAdapter);
    }
}
