package com.infobite.niramayahospital.ui.pharmacy.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.infobite.niramayahospital.R;
import com.infobite.niramayahospital.adapter.farmacy_adapter.AddMedicineAdapter;
import com.infobite.niramayahospital.adapter.farmacy_adapter.CategoryListAdapter;
import com.infobite.niramayahospital.constant.Constant;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.SelectCategoryMainModal;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_medicine_modal.Medcinie;
import com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_medicine_modal.SelectMedicineMainModal;
import com.infobite.niramayahospital.retrofit.RetrofitService;
import com.infobite.niramayahospital.retrofit.WebResponse;
import com.infobite.niramayahospital.utils.Alerts;
import com.infobite.niramayahospital.utils.AppPreference;
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
    private RecyclerView rvCategory;
    private CategoryListAdapter maincategoryListAdapter;
    private AddMedicineAdapter addMedicineAdapter;
    private List<Medcinie> addMedicineList = new ArrayList<>();
    private List<com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.Medcinie> categoryList = new ArrayList<>();
    private AlertDialog alertDialogAddMedicine;
    private Spinner spnCatagery;
    private ArrayAdapter categoryAdapter;
    List<com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.Medcinie> spnCategoryLIst = new ArrayList<>();
    private int rightSelectedTreatmentPosition = 0;
    private String strCategoryName = "", strCategoryId = "";
    private com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.Medcinie medcinie;


    private int position = 0;
    private Button buttonClose, buttonAdd, btnAddCategory, btnEdit, btnUpdateCategory;
    private FloatingActionMenu fbAddMedicine;
    private FloatingActionButton fabCategory, fabAddMedicine;
    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();
    private EditText etCategoryTitle, etCategoryDescription;
    private EditText etMedicineTitle, etMedicineDescription, etStoreBox, etPurchasePrice, etSalePrice, etQuantity,
            etGenericName, etCompany, etSideEffects, etExpireDate, etAvailability, etRecommendation, etDiscount;

    private TextView tvTitleMain, tvTitleView, tvCategoryName, tvMDescriptionView, tvMStoreBoxView, tvMPurchasePriceView, tvMSalePriceView, tvMQuantityView,
            tvMGenericNameView, tvMCompanyView, tvMSideEffectsView, tvreCommendationView, tvMDiscountView;
    private String strCategoryTitle, strCategoryDescription;
    private String strMedicineTitle, strMedicineDescription, strStoreBox, strPurchasePrice, strSalePrice, strQuantity,
            strGenericName, strCompany, strSideEffects, strAvailability, strRecommendation, strDiscount;
    private String strMTitleView, strMDescriptionView, strMStoreBoxVew, strMPurchasePriceView, strMSalePriceView, strMQuantityView,
            strMGenericNameView, strMCompanyView, strMSideEffectsView, strMRecommendationView, strMDiscountView;
    private String strId;


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

    private void viewMedicineDialog(final Medcinie medcinie) {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.view_medicine_dialogbox, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();


        // categorySelect();

        tvTitleMain = dialogBoxView.findViewById(R.id.tvNameTitle);
        tvTitleView = dialogBoxView.findViewById(R.id.tvMNameView);
        tvCategoryName = dialogBoxView.findViewById(R.id.tvMCategoryView);
        tvMStoreBoxView = dialogBoxView.findViewById(R.id.tvMStoreBoxView);
        tvMPurchasePriceView = dialogBoxView.findViewById(R.id.tvMPurchasePriceView);
        tvMSalePriceView = dialogBoxView.findViewById(R.id.tvMSellPriceView);
        tvMQuantityView = dialogBoxView.findViewById(R.id.tvMQuatityView);
        tvMGenericNameView = dialogBoxView.findViewById(R.id.tvMGenericView);
        tvMCompanyView = dialogBoxView.findViewById(R.id.tvMCompanyview);
        tvMSideEffectsView = dialogBoxView.findViewById(R.id.tvMSideEffectView);
        tvMDiscountView = dialogBoxView.findViewById(R.id.tvMeDiscountView);
        tvreCommendationView = dialogBoxView.findViewById(R.id.tvMeRecommendationView);

        tvTitleMain.setText("Add Medicine");
        tvTitleView.setText(medcinie.getTitle());
        tvCategoryName.setText(medcinie.getTitle());
        tvMStoreBoxView.setText(medcinie.getStoreBox());
        tvMPurchasePriceView.setText(medcinie.getCosting());
        tvMSalePriceView.setText(medcinie.getSellingPrice());
        tvMQuantityView.setText(medcinie.getQuantity());
        tvMGenericNameView.setText(medcinie.getGenericName());
        tvMCompanyView.setText(medcinie.getCompanyName());
        tvMSideEffectsView.setText(medcinie.getSideEffect());
        tvMDiscountView.setText(medcinie.getDiscount());
        tvreCommendationView.setText(medcinie.getRecommendation());

        buttonClose = dialogBoxView.findViewById(R.id.btnClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnEdit = dialogBoxView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMedicineDialog(medcinie);
            }
        });
    }

    private void showCategoryDialog() {

        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(true);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.select_category_dialog, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        rvCategory = dialogBoxView.findViewById(R.id.rvCategoryList);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        maincategoryListAdapter = new CategoryListAdapter(categoryList, mContext, this);
        rvCategory.setAdapter(maincategoryListAdapter);
        maincategoryListAdapter.notifyDataSetChanged();

        btnAddCategory = dialogBoxView.findViewById(R.id.btnAddCategory);

        // selectCategoryApi();

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    createCategoryApi();
                addCategoryDialog();
                alertDialog.cancel();
            }
        });
    }

    private void addCategoryDialog() {
        // selectCategoryApi();
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(true);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.add_category_ialog, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        etCategoryTitle = dialogBoxView.findViewById(R.id.etTitleCategory);
        etCategoryDescription = dialogBoxView.findViewById(R.id.etCategoryDescription);
        btnUpdateCategory = dialogBoxView.findViewById(R.id.btnUpdateCategory);
        btnUpdateCategory.setVisibility(View.GONE);
        btnAddCategory = dialogBoxView.findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCategoryApi();
                alertDialog.cancel();
            }
        });
    }

    private void updateCategoryDailog(com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_category_modal.Medcinie medcinie) {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(true);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.add_category_ialog, null);
        dialogBox.setView(dialogBoxView);

        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        strId = medcinie.getId();
        AppPreference.setStringPreference(mContext, Constant.CATEGORY_ID, strId);

        etCategoryTitle = dialogBoxView.findViewById(R.id.etTitleCategory);
        etCategoryTitle.setText(medcinie.getTitle());
        etCategoryDescription = dialogBoxView.findViewById(R.id.etCategoryDescription);
        etCategoryDescription.setText(medcinie.getDescription());
        btnAddCategory = dialogBoxView.findViewById(R.id.btnAddCategory);
        btnAddCategory.setText("Cencel");
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnUpdateCategory = dialogBoxView.findViewById(R.id.btnUpdateCategory);
        btnUpdateCategory.setText("Update");
        btnUpdateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategoryApi();
                alertDialog.cancel();
            }
        });
    }


    private void updateMedicineDialog(Medcinie medcinie) {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(true);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.add_medicine_dialog, null);
        dialogBox.setView(dialogBoxView);

        alertDialogAddMedicine = dialogBox.create();
        alertDialogAddMedicine.show();

        TextView tvTitle = dialogBoxView.findViewById(R.id.tvTitle);
        tvTitle.setText("Update Medicine");
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

        etMedicineTitle.setText(medcinie.getTitle());
        etMedicineDescription.setText(medcinie.getDescription());
        etStoreBox.setText(medcinie.getStoreBox());
        etPurchasePrice.setText(medcinie.getCosting());
        etDiscount.setText(medcinie.getDiscount());
        etSalePrice.setText(medcinie.getSellingPrice());
        etQuantity.setText(medcinie.getQuantity());
        etGenericName.setText(medcinie.getGenericName());
        etCompany.setText(medcinie.getCompanyName());
        etSideEffects.setText(medcinie.getSideEffect());
        etRecommendation.setText(medcinie.getRecommendation());

        ((EditText) dialogBoxView.findViewById(R.id.etMedicineExpireDate))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDatePicker((EditText) dialogBoxView.findViewById(R.id.etMedicineExpireDate));
                    }
                });

        buttonAdd = dialogBoxView.findViewById(R.id.btnAdd);
        buttonAdd.setText("Update");
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMedicineApi();
                alertDialogAddMedicine.cancel();
            }
        });
    }


    private void showMedicineDialog() {
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(true);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogBoxView = li.inflate(R.layout.add_medicine_dialog, null);
        dialogBox.setView(dialogBoxView);

        RelativeLayout rlSpnCatagery = dialogBoxView.findViewById(R.id.rlSpinnerCategory);
        spnCatagery = dialogBoxView.findViewById(R.id.spnCategory);
        categoryAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, spnCategoryLIst);
        spnCatagery.setAdapter(categoryAdapter);
        spnCatagery.setSelection(rightSelectedTreatmentPosition);

        spnCatagery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCategoryName = spnCategoryLIst.get(position).getTitle();
                strCategoryId = spnCategoryLIst.get(position).getId();
                AppPreference.setStringPreference(mContext, Constant.CATEGORY_ID, strCategoryId);
                rightSelectedTreatmentPosition = position;
                //showToast(mContext, position+"#");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        categoryAdapter.notifyDataSetChanged();

        alertDialogAddMedicine = dialogBox.create();
        alertDialogAddMedicine.show();

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
                alertDialogAddMedicine.cancel();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        menus.add(fbAddMedicine);


/*
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
*/


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
                position = Integer.parseInt(v.getTag().toString());
                viewMedicineDialog(addMedicineList.get(position));
                break;
            case R.id.tvCategoryName:
                position = Integer.parseInt(v.getTag().toString());
                updateCategoryDailog(categoryList.get(position));
                break;
            case R.id.fabCategory:
                selectCategoryApi("CAT");
                break;
            case R.id.fabAddMedicine:
//                showMedicineDialog();
                selectCategoryApi("MED");
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
                            AppPreference.setStringPreference(mContext, Constant.CATEGORYNAME, strCategoryTitle);
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
            //  strExpireDate = etExpireDate.getText().toString();
            strRecommendation = etRecommendation.getText().toString();
            strDiscount = etDiscount.getText().toString();

            if (strMedicineTitle.isEmpty()){
                etMedicineTitle.setError("Please enter medicine name!!!");
            }

            RetrofitService.insertMedicine(new Dialog(mContext), retrofitApiClient.insertMedicine(strMedicineTitle, strMedicineDescription, strCategoryName
                    , strPurchasePrice, strSalePrice, strStoreBox, strQuantity, strGenericName, strCompany
                    , strSideEffects, strAvailability, strRecommendation, strDiscount, "", "1", ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (!jsonObject.getBoolean("error")) {
                            Alerts.show(mContext, jsonObject.toString());
                            selectMedicineApi();
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

    private void updateMedicineApi() {
        if (cd.isNetworkAvailable()) {
            String strMedicineId = AppPreference.getStringPreference(mContext, Constant.MEDICINE_ID);

            strMedicineTitle = etMedicineTitle.getText().toString();
            strMedicineDescription = etMedicineDescription.getText().toString();
            strStoreBox = etStoreBox.getText().toString();
            strSalePrice = etSalePrice.getText().toString();
            strPurchasePrice = etPurchasePrice.getText().toString();
            strQuantity = etQuantity.getText().toString();
            strGenericName = etGenericName.getText().toString();
            strCompany = etCompany.getText().toString();
            strSideEffects = etSideEffects.getText().toString();
            //  strExpireDate = etExpireDate.getText().toString();
            strRecommendation = etRecommendation.getText().toString();
            strDiscount = etDiscount.getText().toString();

            RetrofitService.updateMedicineData(new Dialog(mContext), retrofitApiClient.updateMedicine(strMedicineTitle, strMedicineDescription, strCategoryId
                    , strPurchasePrice, strSalePrice, strStoreBox, strQuantity, strGenericName, strCompany
                    , strSideEffects, strAvailability, strRecommendation, strDiscount, "", "1", strMedicineId, ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (!jsonObject.getBoolean("error")) {
                            Alerts.show(mContext, jsonObject.toString());
                            selectMedicineApi();
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
                    addMedicineList.clear();
                    SelectMedicineMainModal mainModal = (SelectMedicineMainModal) result.body();
                    if (!mainModal.getError()) {

                        addMedicineList.addAll(mainModal.getMedcinie());
                        addMedicineAdapter.notifyDataSetChanged();
                        Alerts.show(mContext, mainModal.getMessage());

                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void selectCategoryApi(final String from) {
        if (cd.isNetworkAvailable()) {

            RetrofitService.selectCategoryData(new Dialog(mContext), retrofitApiClient.selectCategory("1"), new WebResponse() {

                @Override
                public void onResponseSuccess(Response<?> result) {
                    categoryList.clear();
                    spnCategoryLIst.clear();
                    SelectCategoryMainModal mainModal = (SelectCategoryMainModal) result.body();
                    if (!mainModal.getError()) {

                        if (from.equals("CAT")){
                            categoryList.addAll(mainModal.getMedcinie());
                            //maincategoryListAdapter.notifyDataSetChanged();
                            showCategoryDialog();
                        }else{
                            spnCategoryLIst.addAll(mainModal.getMedcinie());
                            //categoryAdapter.notifyDataSetChanged();
                            showMedicineDialog();
                            
                        }
/*
                        categoryList.addAll(mainModal.getMedcinie());
                        //   maincategoryListAdapter.notifyDataSetChanged();
                        showCategoryDialog();

                        *//*spnCategoryLIst.addAll(mainModal.getMedcinie());*//*
                        //   categoryAdapter.notifyDataSetChanged();
                        showCategoryDialog();
                        //Alerts.show(mContext, mainModal.getMessage());*/

                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void updateCategoryApi() {
        if (cd.isNetworkAvailable()) {

            //   String strCategoryId = AppPreference.getStringPreference(mContext, Constant.CATEGORY_ID);

            strCategoryTitle = etCategoryTitle.getText().toString();
            strCategoryDescription = etCategoryDescription.getText().toString();


            RetrofitService.updateCategoryData(new Dialog(mContext), retrofitApiClient.updateCategory(strId, strCategoryTitle, strCategoryDescription
                    , "1", "1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody responseBody = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        if (!jsonObject.getBoolean("error")) {
                            Alerts.show(mContext, jsonObject.toString());
                            showCategoryDialog();
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
    
    /*category_id=5, title=PAIN, description=this%20tablet%20normay%20use%20for%20pain%20killer%20just%20like%20hedek%2Cfever%20etc, status=1, hospital_id=1 */


}
