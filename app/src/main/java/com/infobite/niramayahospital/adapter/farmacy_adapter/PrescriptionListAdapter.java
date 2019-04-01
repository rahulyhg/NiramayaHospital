package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.infobite.niramayahospital.R;

import java.util.List;

public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.MyViewHolder> {

    private List<String> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public PrescriptionListAdapter(List<String> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_prescription_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtOpen;
        public ImageView rc_img;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            txtOpen = view.findViewById(R.id.txtOpen);
        }
    }

}
