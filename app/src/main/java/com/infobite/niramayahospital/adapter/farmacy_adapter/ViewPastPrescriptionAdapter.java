package com.infobite.niramayahospital.adapter.farmacy_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;

import java.util.List;

public class ViewPastPrescriptionAdapter extends RecyclerView.Adapter<ViewPastPrescriptionAdapter.MyViewHolder> {

    private List<String> pastPrescriptionList;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public ViewPastPrescriptionAdapter(List<String> pastPrescriptionList, Context mContext, View.OnClickListener onClickListener) {
        this.pastPrescriptionList = pastPrescriptionList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.row_past_prescription, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvOpen.setTag(position);
        holder.tvOpen.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return pastPrescriptionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvOpen, tvDate, tvHospital, tvDrName;

        public MyViewHolder(View view) {
            super(view);
            tvOpen = view.findViewById(R.id.txtOpen);
            tvDate = view.findViewById(R.id.txtDate);
            tvHospital = view.findViewById(R.id.txtHospitalName);
            tvDrName = view.findViewById(R.id.txtDoctorName);

        }
    }

}
