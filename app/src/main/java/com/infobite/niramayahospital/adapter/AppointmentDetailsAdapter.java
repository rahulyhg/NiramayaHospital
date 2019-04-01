package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.models.doctor.appointement.AppointementDatum;
import com.infobite.niramayahospital.models.doctor.appointement.OpdAppointment;

import java.util.ArrayList;

public class AppointmentDetailsAdapter extends RecyclerView.Adapter<AppointmentDetailsAdapter.MyViewHolder> {


    private ArrayList<OpdAppointment> appointmentDataList;
    private Context mContext;
    private View.OnClickListener listener;

    public AppointmentDetailsAdapter(ArrayList<OpdAppointment> appointmentDataList, Context mContext, View.OnClickListener clickListener) {
        this.appointmentDataList = appointmentDataList;
        this.mContext = mContext;
        this.listener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_appointment_details, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OpdAppointment appointmentData = appointmentDataList.get(position);
        holder.tvCount.setText((position+1)+"");
        holder.tvPatientName.setText(": "+appointmentData.getPatient());
        holder.tvPatientType.setText(": Consultation");

        if (appointmentData.getAppointmentPaymentStatus().equals("0")){
            holder.tvStatus.setText(": Unpaid");
        }else{
            holder.tvStatus.setText(": Paid");
        }

        holder.btnCall.setTag(position);
        holder.btnCall.setOnClickListener(listener);
        holder.btnDismiss.setTag(position);
        holder.btnDismiss.setOnClickListener(listener);
        //holder.tvRoomNo.setText(appointmentData.get());

    }

    @Override
    public int getItemCount() {
        return appointmentDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlAvailable;
        private TextView tvCount, tvPatientName, tvPatientType, tvStatus, tvRoomNo;
        private Button btnCall, btnDismiss;

        public MyViewHolder(View view) {
            super(view);

            //rlAvailable = view.findViewById(R.id.rlAvailable);
            tvCount = view.findViewById(R.id.tvCount);
            tvPatientName = view.findViewById(R.id.tvPatientName);
            tvPatientType = view.findViewById(R.id.tvPatientType);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvRoomNo = view.findViewById(R.id.tvRoomNo);

            btnCall = view.findViewById(R.id.btnCall);
            btnDismiss = view.findViewById(R.id.btnDismiss);

        }
    }

}
