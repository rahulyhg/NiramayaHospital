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
import com.infobite.niramayahospital.ui.doctor.fragment.DashboardFragment;
import com.infobite.niramayahospital.utils.InitAppointmentDetail;

import java.util.ArrayList;

public class AppointmentTimeSlotsAdapter extends RecyclerView.Adapter<AppointmentTimeSlotsAdapter.MyViewHolder> {


    private ArrayList<AppointementDatum> appointmentDataList;
    private Context mContext;
    private View.OnClickListener listener;
    private InitAppointmentDetail appointmentDetail;

    private static RelativeLayout lastChecked = null;
    private static int lastCheckedPos = 0;
    private int row_index = 0;

    public AppointmentTimeSlotsAdapter(ArrayList<AppointementDatum> appointmentDataList, Context mContext, View.OnClickListener clickListener, InitAppointmentDetail appointmentDetail) {
        this.appointmentDataList = appointmentDataList;
        this.mContext = mContext;
        this.listener = clickListener;
        this.appointmentDetail = appointmentDetail;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_appointment_time_slots, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        AppointementDatum appointmentData = appointmentDataList.get(position);
        holder.tvAvailTime.setText(appointmentData.getOpdStartTime()+" - "+appointmentData.getOpdEndTime());
        /*holder.rlAvailable.setTag(position);
        holder.rlAvailable.setOnClickListener(listener);*/

        holder.rlAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                notifyDataSetChanged();
                appointmentDetail.initAppointmentDetails(position);
            }
        });

        if(row_index==position){
            holder.rlAvailable.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_rhl2));
            holder.tvAvailTime.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        else
        {
            holder.rlAvailable.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_rhl5));
            holder.tvAvailTime.setTextColor(mContext.getResources().getColor(R.color.black));
        }
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
