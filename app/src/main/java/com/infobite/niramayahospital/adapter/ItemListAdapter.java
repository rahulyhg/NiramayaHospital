package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infobite.niramayahospital.R;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {

    private List<String> itemList;
    private Context mContext;

    public ItemListAdapter(Context mContext, List<String> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.path_row_item_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
        }
    }

}
