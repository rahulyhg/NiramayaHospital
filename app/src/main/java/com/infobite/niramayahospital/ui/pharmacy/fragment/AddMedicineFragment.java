package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.AddMedicineAdapter;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_medicine_modal.Medcinie;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_medicine_modal.SelectMedicineMainModal;
import com.infobite.niramayahospital.retrofit.RetrofitService;
import com.infobite.niramayahospital.retrofit.WebResponse;
import com.infobite.niramayahospital.utils.Alerts;
import com.infobite.niramayahospital.utils.BaseFragment;
import com.infobite.niramayahospital.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgNotification;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSearch;
import static com.infobite.niramayahospital.ui.pharmacy.activity.HomeActivity.imgSort;

public class AddMedicineFragment extends BaseFragment implements View.OnClickListener {

    private View rootview;
    private AddMedicineAdapter addMedicineAdapter;
    private List<Medcinie> addMedicineList = new ArrayList<>();
    private int position = 0;
    private Button buttonClose, buttonAdd, btnAddCategory;
    private FloatingActionMenu fbAddMedicine;
    private FloatingActionButton fabCategory, fabAddMedicine;
    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    private EditText etCategoryTitle, etCategoryDescription;
    private EditText etMedicineTitle, etMedicineDescription, etStoreBox, etPurchasePrice, etSalePrice, etQuantity,
            etGenericName, etCompany, etSideEffects, etExpireDate, etAvailability, etRecommendation, etDiscount;
    private String strCategoryTitle, strCategoryDescription;
    private String strMedicineTitle, strMedicineDescription, strStoreBox, strPurchasePrice, strSalePrice, strQuantity,
            strGenericName, strCompany, strSideEffects, strExpireDate, strAvailability, strRecommendation, strDiscount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_add_medicine, container, false);
        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        RecyclerView rvAddMedicine = rootview.findViewById(R.id.rvAddMedicine);

        rvAddMedicine.setHasFixedSize(true);
        rvAddMedicine.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        addMedicineAdapter = new AddMedicineAdapter(addMedicineList, mContext, this);
        rvAddMedicine.setAdapter(addMedicineAdapter);
        addMedicineAdapter.notifyDataSetChanged();
        init();

        return rootview;
    }

    private void init() {
        //  fabButton();
        imgSearch.setVisibility(View.GONE);
        imgNotification.setVisibility(View.VISIBLE);
        imgSort.setVisibility(View.VISIBLE);


        fbAddMedicine = rootview.findViewById(R.id.menu_addMedicine);
        fbAddMedicine.setOnClickListener(this);

        fabCategory = rootview.findViewById(R.id.fabCategory);
        fabAddMedicine = rootview.findViewById(R.id.fabAddMedicine);

        fabCategory.setOnClickListener(this);
        fabAddMedicine.setOnClickListener(this);

        selectMedicineApi();

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
    private void addCategoryDialog() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.add_category_dialog, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        etCategoryTitle = dialogBoxView.findViewById(R.id.etTitleCategory);
        etCategoryDescription = dialogBoxView.findViewById(R.id.etCategoryDescription);
        btnAddCategory = dialogBoxView.findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCategoryApi();
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

        etMedicineTitle = dialogBoxView.findViewById(R.id.etMedicineTitle);
        etMedicineDescription = dialogBoxView.findViewById(R.id.etMedicineDescription);
        etStoreBox = dialogBoxView.findViewById(R.id.etMedicineStoreBox);
        etPurchasePrice = dialogBoxView.findViewById(R.id.etMedicinePurPrice);
        etSalePrice = dialogBoxView.findViewById(R.id.etMedicineSellingPrice);
        etQuantity = dialogBoxView.findViewById(R.id.etMedicineQty);
        etGenericName = dialogBoxView.findViewById(R.id.etMedicineGenericName);
        etCompany = dialogBoxView.findViewById(R.id.etMedicineCompany);
        etSideEffects = dialogBoxView.findViewById(R.id.etMedicineSideEffects);
        etExpireDate = dialogBoxView.findViewById(R.id.etMedicineExpireDate);
        etRecommendation = dialogBoxView.findViewById(R.id.etMedicineRecommendation);
        etDiscount = dialogBoxView.findViewById(R.id.etMedicineDiscountPrice);

        ((EditText) dialogBoxView.findViewById(R.id.etMedicineExpireDate))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDatePicker((EditText) dialogBoxView.findViewById(R.id.etMedicineExpireDate));
                    }
                });

        buttonAdd = dialogBoxView.findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMedicineApi();
                alertDialog.cancel();
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        menus.add(fbAddMedicine);


        fbAddMedicine.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                String text;
                if (opened) {
                    text = "Menu opened";
                } else {
                    text = "Menu closed";
                }
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });


        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }

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
                viewMedicineDialog();
                position = Integer.parseInt(v.getTag().toString());
                break;
            case R.id.fabCategory:
                addCategoryDialog();
                break;
            case R.id.fabAddMedicine:
                addMedicineDialog();
                break;
        }
    }

    private void createCategoryApi() {
        if (cd.isNetworkAvailable()) {
            strCategoryTitle = etCategoryTitle.getText().toString();
            strCategoryDescription = etCategoryDescription.getText().toString();

            RetrofitService.createCategory(new Dialog(mContext), retrofitApiClient.creteMedicine(strCategoryTitle, strCategoryDescription, "", "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (jsonObject.getString("message").equalsIgnoreCase("Medicine Category created successfull")) {
                            Alerts.show(mContext, jsonObject.toString());
                        } else {
                            String strMsg = jsonObject.getString("message");
                            Alerts.show(mContext, strMsg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void insertMedicineApi() {
        if (cd.isNetworkAvailable()) {
            strMedicineTitle = etMedicineTitle.getText().toString();
            strMedicineDescription = etMedicineDescription.getText().toString();
            strStoreBox = etStoreBox.getText().toString();
            strSalePrice = etSalePrice.getText().toString();
            strPurchasePrice = etPurchasePrice.getText().toString();
            strQuantity = etQuantity.getText().toString();
            strGenericName = etGenericName.getText().toString();
            strCompany = etCompany.getText().toString();
            strSideEffects = etSideEffects.getText().toString();
            strExpireDate = etExpireDate.getText().toString();
            strRecommendation = etRecommendation.getText().toString();
            strDiscount = etDiscount.getText().toString();

            RetrofitService.insertMedicine(new Dialog(mContext), retrofitApiClient.insertMedicine(strMedicineTitle, strMedicineDescription, "1"
                    , strPurchasePrice, strSalePrice, strStoreBox, strQuantity, strGenericName, strCompany
                    , strSideEffects, strAvailability, strRecommendation, strDiscount, "", "1", ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (jsonObject.getString("message").equalsIgnoreCase("medicine created successfull")) {
                            Alerts.show(mContext, jsonObject.toString());
                        } else {
                            String strMsg = jsonObject.getString("message");
                            Alerts.show(mContext, strMsg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void selectMedicineApi() {
        if (cd.isNetworkAvailable()) {

            RetrofitService.selectMedicineData(new Dialog(mContext), retrofitApiClient.selectMedicine("1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    SelectMedicineMainModal mainModal = (SelectMedicineMainModal) result.body();
                    addMedicineList.clear();
                    if (mainModal != null) {
                        addMedicineList = mainModal.getMedcinie();
                        mainModal.getMessage();
                    } else {
                        mainModal.getMessage();
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }
}
