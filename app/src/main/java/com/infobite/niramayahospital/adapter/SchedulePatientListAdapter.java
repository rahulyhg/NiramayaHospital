package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;

import java.util.List;

public class SchedulePatientListAdapter extends RecyclerView.Adapter<SchedulePatientListAdapter.ViewHolder> {
    private Context mContext;
    private List<String> patientList;

    public SchedulePatientListAdapter(Context mContext, List<String> patientList) {
        this.mContext = mContext;
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View rootView = li.inflate(R.layout.row_schedule_patient_list, null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName,tvDay,tvAddress,tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName  = itemView.findViewById(R.id.tvPatientName);
            tvDay  = itemView.findViewById(R.id.tvPatientDay);
            tvAddress  = itemView.findViewById(R.id.tvPatientAddress);
            tvTime  = itemView.findViewById(R.id.tvPatientTime);
        }
    }
}
