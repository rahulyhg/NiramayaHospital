package com.infobite.niramayahospital.ui.pharmacy.fragment.prescription_tabs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.ItemsAdapter;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.ItemsDataModal;
import com.infobite.niramayahospital.utils.BaseFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CreateInvoiceTabFragment extends BaseFragment implements View.OnClickListener {

    private Dialog dialogAddItem, dialogEditItem, dialogPaid;
    private View rootView;
    private List<ItemsDataModal> itemsDataList = new ArrayList<>();

    private ItemsAdapter itemsAdapter;
    private double finalItemCost = 0.0;
    private double allItemTotalAmount = 0.0;
    private double allItemTotalDiscountAmount = 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_create_invoice, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        rootView.findViewById(R.id.llAddItem).setOnClickListener(this);
        rootView.findViewById(R.id.llPaid).setOnClickListener(this);

        RecyclerView recyclerViewItems = rootView.findViewById(R.id.recyclerViewItems);
        recyclerViewItems.setHasFixedSize(true);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(mContext));
        itemsAdapter = new ItemsAdapter(itemsDataList, mContext, this);
        recyclerViewItems.setAdapter(itemsAdapter);
        itemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llAddItem:
                addDialog();
                break;
            case R.id.llPaid:
                payDialog();
                break;
        }
    }

    /*Add items*/
    private void addDialog() {
        dialogAddItem = new Dialog(mContext);
        dialogAddItem.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddItem.setContentView(R.layout.dialog_add_item);

        dialogAddItem.setCanceledOnTouchOutside(true);
        dialogAddItem.setCancelable(true);
        if (dialogAddItem.getWindow() != null)
            dialogAddItem.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        textChangeListener();
        dialogAddItem.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = ((EditText) dialogAddItem.findViewById(R.id.edtItemName)).getText().toString();
                String strUnitCost = ((EditText) dialogAddItem.findViewById(R.id.edtUnitCost)).getText().toString();
                String strQuantity = ((EditText) dialogAddItem.findViewById(R.id.edtQuantity)).getText().toString();
                String strDiscount = ((EditText) dialogAddItem.findViewById(R.id.edtDiscount)).getText().toString();
                String strTax = ((EditText) dialogAddItem.findViewById(R.id.edtTax)).getText().toString();
                String strDescription = ((EditText) dialogAddItem.findViewById(R.id.edtDescription)).getText().toString();

                double unitCost = Double.parseDouble(strUnitCost);
                double quantity = Double.parseDouble(strQuantity);
                double discount = Double.parseDouble(strDiscount);
                double tax = Double.parseDouble(strTax);

                itemsDataList.add(new ItemsDataModal(strName, strDescription, unitCost, quantity,
                        discount, tax, finalItemCost));
                itemsAdapter.notifyDataSetChanged();
                calculateFinalData();
                dialogAddItem.dismiss();
            }
        });

        Window window = dialogAddItem.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogAddItem.show();
    }

    private void textChangeListener() {
        /*Unit cost*/
        ((EditText) dialogAddItem.findViewById(R.id.edtUnitCost)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strUnitCost = ((EditText) dialogAddItem.findViewById(R.id.edtUnitCost)).getText().toString();
                String strQuantity = ((EditText) dialogAddItem.findViewById(R.id.edtQuantity)).getText().toString();
                String strDiscount = ((EditText) dialogAddItem.findViewById(R.id.edtDiscount)).getText().toString();

                if (strUnitCost.isEmpty()) {
                    strUnitCost = "0";
                }

                if (strQuantity.isEmpty()) {
                    strQuantity = "1";
                }

                if (strDiscount.isEmpty()) {
                    strDiscount = "0";
                }

                double unitCost = Double.parseDouble(strUnitCost);
                double quantity = Double.parseDouble(strQuantity);
                double discount = Double.parseDouble(strDiscount);

                double totalQuantityCost = unitCost * quantity;
                double discountCost = (totalQuantityCost * discount) / 100;

                double finalAmount = totalQuantityCost - discountCost;

                String strFinalAmount = new DecimalFormat("##.##").format(finalAmount);
                finalItemCost = Double.parseDouble(strFinalAmount);
                ((TextView) dialogAddItem.findViewById(R.id.txtTotalAmount)).setText("Rs. " + strFinalAmount);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*Quantity*/
        ((EditText) dialogAddItem.findViewById(R.id.edtQuantity)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strUnitCost = ((EditText) dialogAddItem.findViewById(R.id.edtUnitCost)).getText().toString();
                String strQuantity = ((EditText) dialogAddItem.findViewById(R.id.edtQuantity)).getText().toString();
                String strDiscount = ((EditText) dialogAddItem.findViewById(R.id.edtDiscount)).getText().toString();

                if (strUnitCost.isEmpty()) {
                    strUnitCost = "0";
                }

                if (strQuantity.isEmpty()) {
                    strQuantity = "1";
                }

                if (strDiscount.isEmpty()) {
                    strDiscount = "0";
                }

                double unitCost = Double.parseDouble(strUnitCost);
                double quantity = Double.parseDouble(strQuantity);
                double discount = Double.parseDouble(strDiscount);

                double totalQuantityCost = unitCost * quantity;
                double discountCost = (totalQuantityCost * discount) / 100;

                double finalAmount = totalQuantityCost - discountCost;

                String strFinalAmount = new DecimalFormat("##.##").format(finalAmount);
                finalItemCost = Double.parseDouble(strFinalAmount);
                ((TextView) dialogAddItem.findViewById(R.id.txtTotalAmount)).setText("Rs. " + strFinalAmount);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*Discount*/
        ((EditText) dialogAddItem.findViewById(R.id.edtDiscount)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strUnitCost = ((EditText) dialogAddItem.findViewById(R.id.edtUnitCost)).getText().toString();
                String strQuantity = ((EditText) dialogAddItem.findViewById(R.id.edtQuantity)).getText().toString();
                String strDiscount = ((EditText) dialogAddItem.findViewById(R.id.edtDiscount)).getText().toString();

                if (strUnitCost.isEmpty()) {
                    strUnitCost = "0";
                }

                if (strQuantity.isEmpty()) {
                    strQuantity = "1";
                }

                if (strDiscount.isEmpty()) {
                    strDiscount = "0";
                }

                double unitCost = Double.parseDouble(strUnitCost);
                double quantity = Double.parseDouble(strQuantity);
                double discount = Double.parseDouble(strDiscount);

                double totalQuantityCost = unitCost * quantity;
                double discountCost = (totalQuantityCost * discount) / 100;

                double finalAmount = totalQuantityCost - discountCost;

                String strFinalAmount = new DecimalFormat("##.##").format(finalAmount);
                finalItemCost = Double.parseDouble(strFinalAmount);
                ((TextView) dialogAddItem.findViewById(R.id.txtTotalAmount)).setText("Rs. " + strFinalAmount);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calculateFinalData() {
        if (itemsDataList.size() > 0) {
            for (int i = 0; i < itemsDataList.size(); i++) {
                double unitCost = itemsDataList.get(i).getUnitCost();
                double quantity = itemsDataList.get(i).getQuantity();
                allItemTotalAmount = allItemTotalAmount + (unitCost * quantity);
                allItemTotalDiscountAmount = allItemTotalDiscountAmount + itemsDataList.get(i).getTotalAmount();
            }
        }

        double totalDiscountPercent = (allItemTotalDiscountAmount * 100) / allItemTotalAmount;

        ((TextView) rootView.findViewById(R.id.txtDiscount)).setText("Rs. " + totalDiscountPercent);
        ((TextView) rootView.findViewById(R.id.txtTax)).setText("");
        ((TextView) rootView.findViewById(R.id.txtTotal)).setText("Rs. " + allItemTotalDiscountAmount);
        ((TextView) rootView.findViewById(R.id.txtPaid)).setText("Rs. 0.00");

    }

    /*Pay dialog*/
    private void payDialog() {
        dialogPaid = new Dialog(mContext);
        dialogPaid.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPaid.setContentView(R.layout.dialog_paid);

        dialogPaid.setCanceledOnTouchOutside(true);
        dialogPaid.setCancelable(true);
        if (dialogPaid.getWindow() != null)
            dialogPaid.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        String strAmountPay = "Total amount to pay : Rs. " + allItemTotalDiscountAmount;
        ((EditText) dialogPaid.findViewById(R.id.edtTotalAmount)).setText(strAmountPay);

        calculatePayAmount();
        dialogPaid.findViewById(R.id.btnPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPaid.dismiss();
            }
        });

        Window window = dialogPaid.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialogPaid.show();
    }

    private void calculatePayAmount() {
        ((EditText) dialogPaid.findViewById(R.id.edtPayAmount)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strAmount = ((EditText) dialogPaid.findViewById(R.id.edtPayAmount)).getText().toString();
                if (strAmount.isEmpty()) {
                    strAmount = "0.0";
                }
                double amountPay = Double.parseDouble(strAmount);
                double remainingAmount = allItemTotalDiscountAmount - amountPay;

                String strAmountPay = "Total amount to pay : Rs. " + remainingAmount;
                ((EditText) dialogPaid.findViewById(R.id.edtTotalAmount)).setText(strAmountPay);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
