package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;

import java.util.List;

public class PastHistoryAdapter extends RecyclerView.Adapter<PastHistoryAdapter.MyViewHolder> {

    private List<String> pastHistoryList;
    private Context mContext;

    public PastHistoryAdapter(List<String> pastHistoryList, Context mContext) {
        this.pastHistoryList = pastHistoryList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_past_history_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return pastHistoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }

}
