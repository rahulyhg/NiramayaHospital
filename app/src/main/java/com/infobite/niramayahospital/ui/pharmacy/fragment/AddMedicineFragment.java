package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.AddMedicineAdapter;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSort;

public class AddMedicineFragment extends BaseFragment implements View.OnClickListener {
    private View rootview;
    private AddMedicineAdapter addMedicineAdapter;
    private List<String> addMedicineList = new ArrayList<>();
    private int position = 0;
    private Button buttonClose, buttonAdd;
    private FloatingActionButton fbAddMedicine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_add_medicine, container, false);
        init();
        return rootview;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgSort.setVisibility(View.VISIBLE);
        mContext = getActivity();
        RecyclerView rvAddMedicine = rootview.findViewById(R.id.rvAddMedicine);
        fbAddMedicine = rootview.findViewById(R.id.fbAddMedicine);
        fbAddMedicine.setOnClickListener(this);

        for (int i = 0; i <= 6; i++) {
            addMedicineList.add("Paracetamol");
            addMedicineList.add("Pain Killer");
            addMedicineList.add("100 pcs");
            addMedicineList.add("100 pcs");
        }

        rvAddMedicine.setHasFixedSize(true);
        rvAddMedicine.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        addMedicineAdapter = new AddMedicineAdapter(addMedicineList, mContext, this);
        rvAddMedicine.setAdapter(addMedicineAdapter);
        addMedicineAdapter.notifyDataSetChanged();
    }

    private void viewMedicineDialog() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.view_medicine_dialogbox, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        buttonClose = dialogBoxView.findViewById(R.id.btnClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

    private void addMedicineDialog() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.add_medicine_dialog, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        ((EditText) dialogBoxView.findViewById(R.id.etExpireDate))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDatePicker((EditText) dialogBoxView.findViewById(R.id.etExpireDate));
                    }
                });

        buttonAdd = dialogBoxView.findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

    private void openDatePicker(final EditText etDate) {
        int dobYear = Calendar.getInstance().get(Calendar.YEAR);
        int dobMonth = Calendar.getInstance().get(Calendar.MONTH);
        int dobDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(mContext, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String sDay;
                String sMonth;

                if (day <= 2) {
                    sDay = "0" + day;
                } else {
                    sDay = String.valueOf(day);
                }
                if ((month + 1) <= 9) {
                    sMonth = "0" + (month + 1);
                } else {
                    sMonth = String.valueOf((month + 1));
                }

                etDate.setText(sDay + "/" + sMonth + "/" + year);

            }
        }, dobYear, dobMonth, dobDay);
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        dialog.setTitle("");
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivViewMedicine:
                position = Integer.parseInt(v.getTag().toString());
                viewMedicineDialog();
                break;
            case R.id.fbAddMedicine:
                addMedicineDialog();
                break;
        }
    }
}
