package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.models.doctor.appointement.AppointementDatum;

import java.util.ArrayList;

public class AppointmentTimeSlotsAdapter extends RecyclerView.Adapter<AppointmentTimeSlotsAdapter.MyViewHolder> {


    private ArrayList<AppointementDatum> appointmentDataList;
    private Context mContext;
    private View.OnClickListener listener;

    public AppointmentTimeSlotsAdapter(ArrayList<AppointementDatum> appointmentDataList, Context mContext, View.OnClickListener clickListener) {
        this.appointmentDataList = appointmentDataList;
        this.mContext = mContext;
        this.listener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_appointment_time_slots, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AppointementDatum appointmentData = appointmentDataList.get(position);
        holder.tvAvailTime.setText(appointmentData.getOpdStartTime()+" - "+appointmentData.getOpdEndTime());
        holder.rlAvailable.setTag(position);
        holder.rlAvailable.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return appointmentDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlAvailable;
        private TextView tvAvailTime;

        public MyViewHolder(View view) {
            super(view);

            rlAvailable = view.findViewById(R.id.rlAvailable);
            tvAvailTime = view.findViewById(R.id.tvAvailTime);

        }
    }

}
