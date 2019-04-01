package com.infobite.niramayahospital.adapter.pathology_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;

import java.util.List;

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ViewHolder> {
    private Context mContext;
    private List<String> testList;

    public TestListAdapter(Context mContext, List<String> testList) {
        this.mContext = mContext;
        this.testList = testList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootView = li.inflate(R.layout.row_test_list, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTestName, tvTestCode, tvTestSample, tvTestInstruction, tvTestTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestName = itemView.findViewById(R.id.tvTestName);
            tvTestCode = itemView.findViewById(R.id.tvTestCode);
            tvTestSample = itemView.findViewById(R.id.tvTestSample);
            tvTestInstruction = itemView.findViewById(R.id.tvTestInstruction);
            tvTestTime = itemView.findViewById(R.id.tvTestTime);
        }
    }
}
