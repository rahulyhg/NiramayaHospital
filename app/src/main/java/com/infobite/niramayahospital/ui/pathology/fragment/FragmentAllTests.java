package com.infobite.niramayahospital.ui.pathology.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.pathology_adapter.AlphaBateTestListAdapter;
import com.infobite.niramayahospital.adapter.pathology_adapter.TestListAdapter;
import com.infobite.niramayahospital.ui.pathology.interfaces.InitAlphabateList;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentAllTests extends BaseFragment implements View.OnClickListener, InitAlphabateList {
    private View rootView;
    private RecyclerView rvAlphabateTest, rvTestList;
    private AlphaBateTestListAdapter alphaBateTestListAdapter;
    private TestListAdapter testListAdapter;
    private ArrayList<String> alphaBateTestList = new ArrayList<>();
    private List<String> testList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_tests, container, false);
        mContext = getActivity();
        init();
        return rootView;
    }

    private void init() {
        rvAlphabateTest = rootView.findViewById(R.id.rvTestName);

        rvTestList = rootView.findViewById(R.id.rvTestView);

        for (char c = 'A'; c <= 'Z'; ++c)
            alphaBateTestList.add(c + " ");

        rvAlphabateTest.setHasFixedSize(true);
        rvAlphabateTest.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        alphaBateTestListAdapter = new AlphaBateTestListAdapter(mContext, alphaBateTestList, this,this);
        rvAlphabateTest.setAdapter(alphaBateTestListAdapter);
        alphaBateTestListAdapter.notifyDataSetChanged();

        rvTestList = rootView.findViewById(R.id.rvTestView);
        for (int i = 0; i <= 4; i++) {
            testList.add("A");
            testList.add("b");
            testList.add("c");
        }
        rvTestList.setHasFixedSize(true);
        rvTestList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        testListAdapter = new TestListAdapter(mContext, testList);
        rvTestList.setAdapter(testListAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAlphaBateName:
                int position = Integer.parseInt(v.getTag().toString());
             /*   TextView tvAlphaBateName = (TextView) rvAlphabateTest.getChildAt(position).findViewById(R.id.tvAlphaBateName);
              tvAlphaBateName.setBackgroundColor(Color.RED);*/
                break;
        }
    }

    @Override
    public void initAlphabateList(int i) {

    }
}
