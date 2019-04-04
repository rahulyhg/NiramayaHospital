package com.infobite.niramayahospital.ui.pathology.fragment.patient_fragment_module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.ItemListAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends BaseFragment {
    private View rootView;
    private RecyclerView rvBottomItemList, rvSuggetionItemList;
    private ItemListAdapter itemListAdapter, itemListAdapter1;
    private List<String> itemList = new ArrayList<>();
    private List<String> itemList1 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.path_fragment_reports, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {
        rvBottomItemList = rootView.findViewById(R.id.rvBottomItemList);
        rvBottomItemList.setHasFixedSize(true);
        for (int i = 0; i <= 3; i++) {
            itemList.add("hii");
        }

        rvBottomItemList.setLayoutManager(new LinearLayoutManager(mContext));
        itemListAdapter = new ItemListAdapter(mContext, itemList);
        rvBottomItemList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvBottomItemList.setAdapter(itemListAdapter);
        itemListAdapter.notifyDataSetChanged();

        rvSuggetionItemList = rootView.findViewById(R.id.rvSuggetionItemList);
        rvSuggetionItemList.setHasFixedSize(true);
        for (int i = 0; i <= 3; i++) {
            itemList.add("hii");
        }
        for (int i = 0; i <= 3; i++) {
            itemList1.add("hii");
        }

        rvSuggetionItemList.setLayoutManager(new LinearLayoutManager(mContext));
        itemListAdapter1 = new ItemListAdapter(mContext, itemList1);
        rvSuggetionItemList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvSuggetionItemList.setAdapter(itemListAdapter1);
        itemListAdapter1.notifyDataSetChanged();

    }
}
