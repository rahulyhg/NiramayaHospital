package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.infobite.niramayahospital.R;

import java.util.List;

public class AddMedicineAdapter extends RecyclerView.Adapter<AddMedicineAdapter.MyViewHolder> {

    private List<String> addMedicineList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public AddMedicineAdapter(List<String> addMedicineList, Context mContext,View.OnClickListener onClickListener) {
        this.addMedicineList = addMedicineList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_add_medicine, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
           holder.ivViewMedicine.setTag(position);
           holder.ivViewMedicine.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return addMedicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivViewMedicine;

        public MyViewHolder(View view) {
            super(view);

            ivViewMedicine = itemView.findViewById(R.id.ivViewMedicine);
        }
    }

}
