package com.infobite.niramayahospital.ui.fragment.profile_details.patient_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.ViewReportsAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewReportsFragment extends BaseFragment {
    private View rootView;
    private RecyclerView rvViewReports;
    private List<String> reportList = new ArrayList<>();
    private ViewReportsAdapter viewReportsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_view_reports, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        rvViewReports = rootView.findViewById(R.id.rvViewReports);


        for (int i = 0; i <= 8; i++) {
            reportList.add("David Backhem");
            reportList.add("Today");
            reportList.add("Room no 115");
            reportList.add("11:45 PM");
        }

        rvViewReports.setHasFixedSize(true);
        rvViewReports.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        viewReportsAdapter = new ViewReportsAdapter(reportList, mContext);
        rvViewReports.setAdapter(viewReportsAdapter);
        viewReportsAdapter.notifyDataSetChanged();
    }
}
