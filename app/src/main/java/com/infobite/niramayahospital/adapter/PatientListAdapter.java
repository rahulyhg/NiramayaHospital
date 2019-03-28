package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infobite.niramayahospital.R;

import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    private List<String> patientLIst;
    private Context mContext;

    public PatientListAdapter(List<String> patientLIst, Context mContext) {
        this.patientLIst = patientLIst;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_patient_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return patientLIst.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView patientName, patientId, nameAlphabate;

        public MyViewHolder(View view) {
            super(view);

            patientName = view.findViewById(R.id.tvPatientNameList);
            patientId = view.findViewById(R.id.tvPatientId);
            nameAlphabate = view.findViewById(R.id.tvAlphabate);
        }
    }

}
