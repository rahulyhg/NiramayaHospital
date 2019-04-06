package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_medicine_modal.Medcinie;
import com.infobite.niramayahospital.utils.AppPreference;

import java.util.List;

public class AddMedicineAdapter extends RecyclerView.Adapter<AddMedicineAdapter.MyViewHolder> {

    private List<Medcinie> addMedicineList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public AddMedicineAdapter(List<Medcinie> addMedicineList, Context mContext, View.OnClickListener onClickListener) {
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
        Medcinie medcinielist = addMedicineList.get(position);

        String strCategoryName = AppPreference.getStringPreference(mContext, Constant.CATEGORYNAME);

        holder.tvMedicineName.setText(medcinielist.getTitle());
        holder.tvMedicineCategory.setText(strCategoryName);
        holder.tvMedicineQuantity.setText(medcinielist.getQuantity());
        holder.tvMedicineStocks.setText(medcinielist.getStoreBox());
        String strMedicineId = medcinielist.getId();
        AppPreference.setStringPreference(mContext,Constant.MEDICINE_ID,strMedicineId);


        holder.ivViewMedicine.setTag(position);
        holder.ivViewMedicine.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return addMedicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivViewMedicine;
        private TextView tvMedicineName, tvMedicineCategory, tvMedicineQuantity, tvMedicineStocks;

        public MyViewHolder(View view) {
            super(view);

            ivViewMedicine = itemView.findViewById(R.id.ivViewMedicine);

            tvMedicineName = itemView.findViewById(R.id.tvMedicineName);
            tvMedicineCategory = itemView.findViewById(R.id.tvMedicineCategory);
            tvMedicineQuantity = itemView.findViewById(R.id.tvMedicineQuantity);
            tvMedicineStocks = itemView.findViewById(R.id.tvMedicineStocks);
        }
    }

}
