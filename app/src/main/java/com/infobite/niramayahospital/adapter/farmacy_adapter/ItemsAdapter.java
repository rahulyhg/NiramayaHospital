package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.ItemsDataModal;

import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private List<ItemsDataModal> itemsDataList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public ItemsAdapter(List<ItemsDataModal> itemsDataList, Context mContext, View.OnClickListener onClickListener) {
        this.itemsDataList = itemsDataList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_invoice_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtItemName.setText(itemsDataList.get(position).getName());

        String quantity = itemsDataList.get(position).getQuantity() + "";
        holder.txtQuantity.setText(quantity);

        String quantityPrice = itemsDataList.get(position).getQuantity() + " x " + itemsDataList.get(position).getUnitCost();
        holder.txtQuantityPrice.setText(quantityPrice);

        String totalAmount = "Rs. " + itemsDataList.get(position).getTotalAmount();
        holder.txtTotalPrice.setText(totalAmount);
    }

    @Override
    public int getItemCount() {
        return itemsDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtItemName, txtQuantity, txtQuantityPrice, txtTotalPrice;

        public MyViewHolder(View view) {
            super(view);

            txtItemName = itemView.findViewById(R.id.txtItemName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtQuantityPrice = itemView.findViewById(R.id.txtQuantityPrice);
            txtTotalPrice = itemView.findViewById(R.id.txtTotalPrice);
        }
    }

}
