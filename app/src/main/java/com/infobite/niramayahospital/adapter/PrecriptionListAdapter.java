package com.infobite.niramayahospital.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.models.CreatePrescriptionModel;

import java.util.ArrayList;
import java.util.List;

public class PrecriptionListAdapter extends RecyclerView.Adapter<PrecriptionListAdapter.MyViewHolder> {

    private ArrayList<CreatePrescriptionModel> prescriptionList;
    private Context mContext;

    public PrecriptionListAdapter(ArrayList<CreatePrescriptionModel> pastHistoryList, Context mContext) {
        this.prescriptionList = pastHistoryList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_precription_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CreatePrescriptionModel prescriptionModel = prescriptionList.get(position);
        String type =prescriptionModel.getType();
        switch (type) {
            case "CANVAS":

                holder.llMedicine.setVisibility(View.GONE);
                holder.llTest.setVisibility(View.GONE);
                holder.llCanvas.setVisibility(View.VISIBLE);
                Bitmap bmpMedicine = BitmapFactory.decodeFile(prescriptionModel.getMedicineImagePath());
                Bitmap bmpDose = BitmapFactory.decodeFile(prescriptionModel.getDoseImagePath());
                holder.ivMedicine.setImageBitmap(bmpMedicine);
                holder.ivDose.setImageBitmap(bmpDose);

                break;
            case "TEXT":

                holder.llMedicine.setVisibility(View.VISIBLE);
                holder.llTest.setVisibility(View.GONE);
                holder.llCanvas.setVisibility(View.GONE);
                holder.tvMedicine.setText(prescriptionModel.getMedicine());
                holder.tvDose.setText(prescriptionModel.getDose());

                break;
            case "TEST":

                holder.llMedicine.setVisibility(View.GONE);
                holder.llTest.setVisibility(View.VISIBLE);
                holder.llCanvas.setVisibility(View.GONE);
                holder.tvTest.setText(prescriptionModel.getTest());

                break;
        }

    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llMedicine,llCanvas, llTest;
        private ImageView ivMedicine, ivDose;
        private TextView tvMedicine, tvDose, tvTest;

        public MyViewHolder(View view) {
            super(view);

            llCanvas = (LinearLayout)view.findViewById(R.id.llCanvas);
            ivMedicine = (ImageView) view.findViewById(R.id.ivMedicine);
            ivDose = (ImageView) view.findViewById(R.id.ivDose);

            llMedicine = (LinearLayout)view.findViewById(R.id.llMedicine);
            tvMedicine = (TextView)view.findViewById(R.id.tvMedicine);
            tvDose = (TextView)view.findViewById(R.id.tvDose);

            llTest = (LinearLayout)view.findViewById(R.id.llTest);
            tvTest = (TextView)view.findViewById(R.id.tvTest);

        }
    }

}
