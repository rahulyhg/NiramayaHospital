package com.infobite.niramayahospital.ui.fragment.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.PastHistoryAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class PastHistoryFragment extends BaseFragment {
    private View rootView;
    private PastHistoryAdapter pastHistoryAdapter;
    private List<String> pastHistoryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_past_history, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        RecyclerView rvViewReports = rootView.findViewById(R.id.rvPastHistory);

        rvViewReports.setHasFixedSize(true);

        for (int i = 0; i <= 8; i++) {
            pastHistoryList.add("2/219");
            pastHistoryList.add("Apollo Hospital");
            pastHistoryList.add("Dr. Amit Rana");
        }

        rvViewReports.setLayoutManager(new GridLayoutManager(mContext, 2));
        pastHistoryAdapter = new PastHistoryAdapter(pastHistoryList, mContext);
        rvViewReports.setAdapter(pastHistoryAdapter);
        pastHistoryAdapter.notifyDataSetChanged();
    }
}
