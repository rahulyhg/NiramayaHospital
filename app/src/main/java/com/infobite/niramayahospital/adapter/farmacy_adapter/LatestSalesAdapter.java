package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.infobite.niramayahospital.R;

import java.util.List;

public class LatestSalesAdapter extends RecyclerView.Adapter<LatestSalesAdapter.MyViewHolder> {

    private List<String> latestSalesList;
    private Context mContext;
    public LatestSalesAdapter(List<String> latestSalesList, Context mContext) {
        this.latestSalesList = latestSalesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_latest_sales, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*holder.restaurent_name.setText(vendorLists.get(position).getVendorName());
        holder.restaurent_address.setText(vendorLists.get(position).getVendorStreet());
        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        String sImg = Constant.BASE_URL + vendorLists.get(position).getVendorLogo();
        Glide.with(mContext).load(sImg)
                .into(holder.rc_img);*/
        /*holder.txtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HospitalDetailActivity.class);
                intent.putExtra("from", "detail");
                mContext.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return latestSalesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  tvLatestSalePrice,tvLatestSaleDate;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            tvLatestSaleDate = view.findViewById(R.id.tvLatestSaleDate);
            tvLatestSaleDate = view.findViewById(R.id.tvLatestSalePrice);
        }
    }

}
