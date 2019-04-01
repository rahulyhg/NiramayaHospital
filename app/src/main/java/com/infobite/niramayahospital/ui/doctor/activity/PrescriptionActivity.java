package com.infobite.niramayahospital.ui.doctor.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.infobite.niramayahospital.R;
import com.crashlytics.android.Crashlytics;
import com.infobite.niramayahospital.adapter.PrescriptionAdvisedListAdapter;
import com.infobite.niramayahospital.adapter.PrecriptionListAdapter;
import com.infobite.niramayahospital.models.CreatePrescriptionAdvisedModel;
import com.infobite.niramayahospital.models.CreatePrescriptionModel;
import com.infobite.niramayahospital.models.doctor.medicine_pathology.Medciinie;
import com.infobite.niramayahospital.models.doctor.medicine_pathology.MedicinePathologyMainModal;
import com.infobite.niramayahospital.models.doctor.medicine_pathology.PathologyTest;
import com.infobite.niramayahospital.models.user.UserData;
import com.infobite.niramayahospital.models.user.UserModel;
import com.infobite.niramayahospital.retrofit.RetrofitService;
import com.infobite.niramayahospital.retrofit.WebResponse;
import com.infobite.niramayahospital.utils.Alerts;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.DigitalSignature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PrescriptionActivity extends BaseActivity implements View.OnClickListener {

    public static FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private RelativeLayout leftDrawer;
    private RelativeLayout rightDrawer;
    private EditText etChiefComplaint, etBP, etHeartRate, etRespRate, etTemp, etPainScore;
    private TextView tvDoctorName, tvHospitalName, tvScheduleDate;
    private LinearLayout llMedicine, llDose;
    private Button btnSubmit;
    private RecyclerView rvMedicine, rvTreatmentAdvise;
    private TextView tvClearMedicine, tvClearDose;
    private ArrayList<CreatePrescriptionModel> prescriptionList;
    private PrecriptionListAdapter prescriptionAdapter;
    private ArrayList<CreatePrescriptionAdvisedModel> prescriptionAdvisedList;
    private PrescriptionAdvisedListAdapter prescriptionAdvisedAdapter;

    String[] arr = {"completenate", "cometriq", "companion", "cpmpleat", "compazine", "complera"};
    String[] arrTreatment = {"Treatment Given", "Treatment Advised"};
    private AutoCompleteTextView autoCompleteSearch, autoCompletePathologySearch;
    private EditText etDose, etTest;
    private String leftSelectedTreatment = "";
    private String rightSelectedTreatment = "";
    private String selectedMedicineId = "";
    private String selectedTestId = "";
    private int leftSelectedTreatmentPosition = 0;
    private int rightSelectedTreatmentPosition = 0;

    private Spinner spnLeftTreatment, spnRightTreatment;

    private String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Niramaya/";
    private String folder_main = "Niramaya";
    private String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
    private String doseStoredPath = "";
    private String medicineStoredPath = "";
    private DigitalSignature cMedicine, cDose;
    private File file;
    private String appointmentId = "";
    private UserData userData;

    private ArrayList<Medciinie> medicineList;
    private ArrayList<PathologyTest> pathologyTestList;
    ArrayAdapter<Medciinie> adapter;
    ArrayAdapter<PathologyTest> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_prescription);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Prescription");


        try {
            appointmentId = getIntent().getExtras().getString("AppointmentId");
        } catch (Exception e) {
            e.printStackTrace();
            appointmentId = "";
        }

        userData = UserModel.getUserModel().getUser();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (RelativeLayout) findViewById(R.id.nav_left);
        rightDrawer = (RelativeLayout) findViewById(R.id.nav_right);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        llMedicine = (LinearLayout) findViewById(R.id.llMedicine);
        llDose = (LinearLayout) findViewById(R.id.llDose);
        btnSubmit = findViewById(R.id.btnSubmit);

        autoCompleteSearch = (AutoCompleteTextView) findViewById(R.id.edtSearch);
        etDose = (EditText) findViewById(R.id.etDose);
        autoCompletePathologySearch = (AutoCompleteTextView) findViewById(R.id.acPathologyTest);

        tvClearMedicine = (TextView) findViewById(R.id.tvClearMedicine);
        tvClearDose = (TextView) findViewById(R.id.tvClearDose);
        rvMedicine = (RecyclerView) findViewById(R.id.rvMedicine);
        rvTreatmentAdvise = (RecyclerView) findViewById(R.id.rvTreatmentAdvise);

        spnLeftTreatment = (Spinner) findViewById(R.id.spnLeftTreatment);
        spnRightTreatment = (Spinner) findViewById(R.id.spnRightTreatment);

        etChiefComplaint = (EditText) findViewById(R.id.etChiefComplaint);
        etBP = (EditText) findViewById(R.id.etBP);
        etHeartRate = (EditText) findViewById(R.id.etHeartRate);
        etRespRate = (EditText) findViewById(R.id.etRespRate);
        etTemp = (EditText) findViewById(R.id.etTemp);
        etPainScore = (EditText) findViewById(R.id.etPainScore);

        tvClearDose.setOnClickListener(this);
        tvClearMedicine.setOnClickListener(this);

        ((ImageView) findViewById(R.id.ivAddNew)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.ivSearch)).setOnClickListener(this);

        /*((Button)findViewById(R.id.btnAdd)).setOnClickListener(this);*/
        ((Button) findViewById(R.id.btnAddMedicine)).setOnClickListener(this);
        ((Button) findViewById(R.id.btnAddTest)).setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        ((ImageView) findViewById(R.id.imgEditPrescription)).setOnClickListener(this);

        prescriptionList = new ArrayList<>();
        prescriptionAdvisedList = new ArrayList<>();
        medicinePathologyApi();
        initViews();

    }

    private void initViews() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        prescriptionAdapter = new PrecriptionListAdapter(prescriptionList, mContext);
        rvMedicine.setLayoutManager(layoutManager);
        rvMedicine.setAdapter(prescriptionAdapter);
        prescriptionAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager adLayoutManager = new LinearLayoutManager(mContext);
        prescriptionAdvisedAdapter = new PrescriptionAdvisedListAdapter(prescriptionAdvisedList, mContext);
        rvTreatmentAdvise.setLayoutManager(adLayoutManager);
        rvTreatmentAdvise.setAdapter(prescriptionAdvisedAdapter);
        prescriptionAdvisedAdapter.notifyDataSetChanged();

        if (prescriptionList.size() > 0 || prescriptionAdvisedList.size() > 0) {
            btnSubmit.setVisibility(View.VISIBLE);
        }

        file = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!file.exists()) {
            file.mkdirs();
            /*if (file.mkdirs()){
                Toast.makeText(this@SignetureActivity, "Folder created successfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@SignetureActivity, "Folder not created", Toast.LENGTH_LONG).show()
            }*/
        }

        cMedicine = new DigitalSignature(PrescriptionActivity.this, null, llMedicine);
        cMedicine.setBackgroundColor(Color.WHITE);
        llMedicine.addView(cMedicine, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        cDose = new DigitalSignature(PrescriptionActivity.this, null, llDose);
        cDose.setBackgroundColor(Color.WHITE);
        llDose.addView(cDose, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ((Button) findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isMedicine = false;
                boolean isDose = false;
                doseStoredPath = DIRECTORY + "Dose" + System.currentTimeMillis() + ".png";
                medicineStoredPath = DIRECTORY + "Medicine" + System.currentTimeMillis() + ".png";
                if (cMedicine.save(llMedicine, medicineStoredPath)) {
                    isMedicine = true;
                }
                if (cDose.save(llDose, doseStoredPath)) {
                    isDose = true;
                }
                if (isMedicine && isDose) {
                    if (leftSelectedTreatmentPosition == 0) {
                        CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
                        prescriptionModel.setType("CANVAS");
                        prescriptionModel.setMedicineImagePath(medicineStoredPath);
                        prescriptionModel.setDoseImagePath(doseStoredPath);
                        prescriptionList.add(prescriptionModel);
                        prescriptionAdapter.notifyDataSetChanged();
                        if (prescriptionList.size() > 0) {
                            btnSubmit.setVisibility(View.VISIBLE);
                        }
                        drawer.closeDrawer(Gravity.START);
                        cMedicine.clear();
                        cDose.clear();
                    } else {
                        CreatePrescriptionAdvisedModel prescriptionModel = new CreatePrescriptionAdvisedModel();
                        prescriptionModel.setType("CANVAS");
                        prescriptionModel.setMedicineImagePath(medicineStoredPath);
                        prescriptionModel.setDoseImagePath(doseStoredPath);
                        prescriptionAdvisedList.add(prescriptionModel);
                        prescriptionAdvisedAdapter.notifyDataSetChanged();
                        if (prescriptionAdvisedList.size() > 0) {
                            btnSubmit.setVisibility(View.VISIBLE);
                        }
                        drawer.closeDrawer(Gravity.START);
                        cMedicine.clear();
                        cDose.clear();
                    }
                    //Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    showToast("Please try again...");
                }
            }
        });

        ArrayAdapter leftTreatmentAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, arrTreatment);
        spnLeftTreatment.setAdapter(leftTreatmentAdapter);
        spnLeftTreatment.setSelection(rightSelectedTreatmentPosition);
        spnLeftTreatment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leftSelectedTreatment = String.valueOf(arrTreatment[position]);
                leftSelectedTreatmentPosition = position;
                //showToast(mContext, position+"#");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        leftTreatmentAdapter.notifyDataSetChanged();

        ArrayAdapter rightTreatmentAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, arrTreatment);
        spnRightTreatment.setAdapter(rightTreatmentAdapter);
        spnRightTreatment.setSelection(rightSelectedTreatmentPosition);
        spnRightTreatment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rightSelectedTreatment = arrTreatment[position];
                rightSelectedTreatmentPosition = position;
                //showToast(mContext, position+"#");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rightTreatmentAdapter.notifyDataSetChanged();

        /*ArrayAdapter putAdapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, propertyUsageTypeArray);
        spnPUT.setAdapter(putAdapter);
        spnPUT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0) {
                    propertyUsageType = propertyUsageTypeArray[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        putAdapter.notifyDataSetChanged();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivAddNew:
                drawer.openDrawer(Gravity.START);
                break;
            case R.id.ivSearch:
                drawer.openDrawer(Gravity.END);
                break;
            case R.id.btnAddMedicine:
                initAddMedicine();
                //initPathologyTest();
                break;
            case R.id.btnAddTest:
                drawer.closeDrawer(Gravity.END);
                initAddTest();
                break;
            case R.id.tvClearMedicine:
                cMedicine.clear();
                break;
            case R.id.tvClearDose:
                cDose.clear();
                break;
            case R.id.imgEditPrescription:
                Intent intent = new Intent(mContext, HomeActivity.class);
                intent.putExtra("from", "Prescription");
                startActivity(intent);
                break;
            case R.id.btnSubmit:
                submitPrescription();
                break;

        }
    }

    private void initAddTest() {
        String test = autoCompletePathologySearch.getText().toString().trim();
        if (test.isEmpty()) {
            showToast("First enter a test.");
        } else {
            if (rightSelectedTreatmentPosition == 0) {
                CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
                prescriptionModel.setType("TEST");
                prescriptionModel.setTest(test);
                prescriptionModel.setId(selectedTestId);
                prescriptionList.add(prescriptionModel);
                prescriptionAdapter.notifyDataSetChanged();
                if (prescriptionList.size() > 0) {
                    btnSubmit.setVisibility(View.VISIBLE);
                }
            } else {
                CreatePrescriptionAdvisedModel prescriptionModel = new CreatePrescriptionAdvisedModel();
                prescriptionModel.setType("TEST");
                prescriptionModel.setTest(test);
                prescriptionModel.setId(selectedTestId);
                prescriptionAdvisedList.add(prescriptionModel);
                prescriptionAdvisedAdapter.notifyDataSetChanged();
                if (prescriptionAdvisedList.size() > 0) {
                    btnSubmit.setVisibility(View.VISIBLE);
                }
            }
            autoCompletePathologySearch.setText("");
            drawer.closeDrawer(Gravity.END);
        }
    }

    private void initAddMedicine() {
        String medicine = autoCompleteSearch.getText().toString().trim();
        String dose = etDose.getText().toString().trim();
        if (medicine.isEmpty()) {
            showToast("First enter a medicine name.");
        } else if (dose.isEmpty()) {
            showToast("Please add dose.");
        } else {
            if (rightSelectedTreatmentPosition == 0) {
                CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
                prescriptionModel.setType("TEXT");
                prescriptionModel.setMedicine(medicine);
                prescriptionModel.setId(selectedMedicineId);
                prescriptionModel.setDose(dose);
                prescriptionList.add(prescriptionModel);
                prescriptionAdapter.notifyDataSetChanged();
                if (prescriptionList.size() > 0) {
                    btnSubmit.setVisibility(View.VISIBLE);
                }
            } else {
                CreatePrescriptionAdvisedModel prescriptionModel = new CreatePrescriptionAdvisedModel();
                prescriptionModel.setType("TEXT");
                prescriptionModel.setMedicine(medicine);
                prescriptionModel.setId(selectedMedicineId);
                prescriptionModel.setDose(dose);
                prescriptionAdvisedList.add(prescriptionModel);
                prescriptionAdvisedAdapter.notifyDataSetChanged();
                if (prescriptionAdvisedList.size() > 0) {
                    btnSubmit.setVisibility(View.VISIBLE);
                }
            }
            autoCompleteSearch.setText("");
            etDose.setText("");
            drawer.closeDrawer(Gravity.END);
        }
    }

    private void initPathologyTest() {
        String pathTest = autoCompletePathologySearch.getText().toString().trim();
        String dose = etDose.getText().toString().trim();
        if (pathTest.isEmpty()) {
            showToast("First enter a pathology test name.");
        } else if (dose.isEmpty()) {
            showToast("Please add dose.");
        } else {
            CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
            prescriptionModel.setType("TEST");
            prescriptionModel.setMedicine(pathTest);
            prescriptionModel.setDose(dose);
            prescriptionList.add(prescriptionModel);
            prescriptionAdapter.notifyDataSetChanged();
            if (prescriptionList.size() > 0) {
                btnSubmit.setVisibility(View.VISIBLE);
            }
            autoCompletePathologySearch.setText("");
            etDose.setText("");
            drawer.closeDrawer(Gravity.END);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void setAutoCompleteSearchData() {
        adapter = new ArrayAdapter<Medciinie>
                (this, R.layout.row_auto_complete_text, medicineList);
        autoCompleteSearch.setThreshold(2);
        autoCompleteSearch.setAdapter(adapter);
        autoCompleteSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                selectedMedicineId = medicineList.get(pos).getMedicineId();
                //Toast.makeText(mContext,medicineList.get(pos).getMedicineId()+" : "+medicineList.get(pos).getMedicineTitle(), Toast.LENGTH_LONG).show();

            }
        });
        adapter.notifyDataSetChanged();
    }

    private void setAutoCompletePathTestData() {
        adapter1 = new ArrayAdapter<PathologyTest>
                (this, R.layout.row_auto_complete_text, pathologyTestList);
        autoCompletePathologySearch.setThreshold(2);
        autoCompletePathologySearch.setAdapter(adapter1);
        autoCompletePathologySearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                selectedTestId = pathologyTestList.get(pos).getPathologyTestId();

            }
        });
        adapter1.notifyDataSetChanged();
    }

    private void medicinePathologyApi() {

        if (cd.isNetworkAvailable()) {

            RetrofitService.getMedicinePathologyList(new Dialog(mContext), retrofitApiClient.medicinePathology("1"), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    MedicinePathologyMainModal mainModal = (MedicinePathologyMainModal) result.body();
                    if (mainModal != null) {
                        Alerts.show(mContext, mainModal.getMessage());
                        if (mainModal.getMedciinie() != null) {
                            medicineList = (ArrayList<Medciinie>) mainModal.getMedciinie();
                            setAutoCompleteSearchData();

                        } else {
                            Alerts.show(mContext, mainModal.getMessage());
                        }
                        if (mainModal.getPathologyTest() != null) {
                            pathologyTestList = (ArrayList<PathologyTest>) mainModal.getPathologyTest();
                            setAutoCompletePathTestData();
                        }
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

    private void submitPrescription() {
        //showToast(mContext, "In Process...");
        //etChiefComplaint, etBP, etHeartRate, etRespRate, etTemp, etPainScore
        String chiefComplaint = etChiefComplaint.getText().toString();
        String bp = etBP.getText().toString();
        String heartRate = etHeartRate.getText().toString();
        String respRate = etRespRate.getText().toString();
        String temp = etTemp.getText().toString();
        String painScore = etPainScore.getText().toString();

        JSONArray medicineArray = new JSONArray();
        JSONArray testArray = new JSONArray();

        for (int i = 0; i < prescriptionList.size(); i++) {

            JSONObject medicineNameObject = new JSONObject();
            JSONObject medicineImageObject = new JSONObject();
            JSONObject testObject = new JSONObject();

            try {
                if (prescriptionList.get(i).getType().equals("CANVAS")) {

                    String medicine64 = convertToBase64(prescriptionList.get(i).getMedicineImagePath());
                    String dose64 = convertToBase64(prescriptionList.get(i).getDoseImagePath());

                    medicineImageObject.put("medicine_image", medicine64);
                    medicineImageObject.put("medicine_dose", dose64);
                    medicineImageObject.put("preception_type", "0");

                    medicineArray.put(medicineImageObject);

                } else if (prescriptionList.get(i).getType().equals("TEXT")) {
                    medicineNameObject.put("medicine_id", prescriptionList.get(i).getId());
                    medicineNameObject.put("medicine_name", prescriptionList.get(i).getMedicine());
                    medicineNameObject.put("dose", prescriptionList.get(i).getDose());
                    medicineNameObject.put("preception_type", "0");

                    medicineArray.put(medicineNameObject);

                } else if (prescriptionList.get(i).getType().equals("TEST")) {
                    testObject.put("test_name", prescriptionList.get(i).getTest());
                    testObject.put("test_id", prescriptionList.get(i).getId());

                    testArray.put(testObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        for (int i = 0; i < prescriptionAdvisedList.size(); i++) {

            JSONObject medicineNameObject = new JSONObject();
            JSONObject medicineImageObject = new JSONObject();
            JSONObject testObject = new JSONObject();

            try {
                if (prescriptionAdvisedList.get(i).getType().equals("CANVAS")) {

                    String medicine64 = convertToBase64(prescriptionAdvisedList.get(i).getMedicineImagePath());
                    String dose64 = convertToBase64(prescriptionAdvisedList.get(i).getDoseImagePath());

                    medicineImageObject.put("medicine_image", medicine64);
                    medicineImageObject.put("medicine_dose", dose64);
                    medicineImageObject.put("preception_type", "1");

                    medicineArray.put(medicineImageObject);

                } else if (prescriptionAdvisedList.get(i).getType().equals("TEXT")) {
                    medicineNameObject.put("medicine_id", prescriptionAdvisedList.get(i).getId());
                    medicineNameObject.put("medicine_name", prescriptionAdvisedList.get(i).getMedicine());
                    medicineNameObject.put("dose", prescriptionAdvisedList.get(i).getDose());
                    medicineNameObject.put("preception_type", "1");
                    medicineArray.put(medicineNameObject);
                } else if (prescriptionAdvisedList.get(i).getType().equals("TEST")) {
                    testObject.put("test_name", prescriptionAdvisedList.get(i).getTest());
                    testObject.put("test_id", prescriptionAdvisedList.get(i).getId());

                    testArray.put(testObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        if (cd.isNetworkAvailable()) {
            RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.submitPaitaintPrescription(appointmentId,
                    chiefComplaint, "", bp, heartRate, respRate, temp, painScore, "", "",
                    userData.getId(), "", "normal", testArray.toString(), medicineArray.toString()), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    ResponseBody response = (ResponseBody) result.body();
                    try {
                        JSONObject jsonObject = new JSONObject(response.string());
                        if (!jsonObject.getBoolean("error")) {
                            showToast(mContext, jsonObject.get("message").toString());
                            /*startActivity(new Intent(mContext, PrescriptionActivity.class).putExtra("AppointmentId", opdAppointment.getAppointmentId()));*/
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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

    private String convertToBase64(String path) {

        Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }
}
