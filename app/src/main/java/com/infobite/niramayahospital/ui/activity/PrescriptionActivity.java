package com.infobite.niramayahospital.ui.activity;

import android.graphics.Color;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infobite.niramayahospital.R;
import com.crashlytics.android.Crashlytics;
import com.infobite.niramayahospital.adapter.PrecriptionListAdapter;
import com.infobite.niramayahospital.models.CreatePrescriptionModel;
import com.infobite.niramayahospital.utils.BaseActivity;
import com.infobite.niramayahospital.utils.DigitalSignature;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

public class PrescriptionActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private RelativeLayout leftDrawer;
    private RelativeLayout rightDrawer;
    private LinearLayout llMedicine, llDose;
    private RecyclerView rvMedicine;
    private TextView tvClearMedicine, tvClearDose;
    private ArrayList<CreatePrescriptionModel> prescriptionList;
    private PrecriptionListAdapter prescriptionAdapter;

    String[] arr = { "completenate", "cometriq","companion","cpmpleat", "compazine", "complera"};
    private AutoCompleteTextView autoCompleteSearch;
    private EditText etDose, etTest;

    private String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Niramaya/";
    private String folder_main = "Niramaya";
    private String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
    private String doseStoredPath = "";
    private String medicineStoredPath = "";
    private DigitalSignature cMedicine, cDose;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_prescription);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Prescription");


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (RelativeLayout) findViewById(R.id.nav_left);
        rightDrawer = (RelativeLayout) findViewById(R.id.nav_right);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        llMedicine = (LinearLayout) findViewById(R.id.llMedicine);
        llDose = (LinearLayout) findViewById(R.id.llDose);

        autoCompleteSearch = (AutoCompleteTextView) findViewById(R.id.edtSearch);
        etDose = (EditText) findViewById(R.id.etDose);
        etTest = (EditText) findViewById(R.id.etTest);

        tvClearMedicine = (TextView) findViewById(R.id.tvClearMedicine);
        tvClearDose = (TextView) findViewById(R.id.tvClearDose);
        rvMedicine = (RecyclerView) findViewById(R.id.rvMedicine);

        tvClearDose.setOnClickListener(this);
        tvClearMedicine.setOnClickListener(this);

        ((ImageView)findViewById(R.id.ivAddNew)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.ivSearch)).setOnClickListener(this);

        /*((Button)findViewById(R.id.btnAdd)).setOnClickListener(this);*/
        ((Button)findViewById(R.id.btnAddMedicine)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnAddTest)).setOnClickListener(this);

        prescriptionList = new ArrayList<>();

        initViews();

    }

    private void initViews() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        prescriptionAdapter = new PrecriptionListAdapter(prescriptionList, mContext);
        rvMedicine.setLayoutManager(layoutManager);
        rvMedicine.setAdapter(prescriptionAdapter);
        prescriptionAdapter.notifyDataSetChanged();


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

        ((Button)findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isMedicine = false;
                boolean isDose = false;
                doseStoredPath = DIRECTORY + "Dose"+System.currentTimeMillis() + ".png";
                medicineStoredPath = DIRECTORY + "Medicine"+System.currentTimeMillis() + ".png";
                if(cMedicine.save(llMedicine, medicineStoredPath)){
                    isMedicine = true;
                }
                if(cDose.save(llDose, doseStoredPath)){
                    isDose = true;
                }
                if (isMedicine && isDose){
                    CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
                    prescriptionModel.setType("CANVAS");
                    prescriptionModel.setMedicineImagePath(medicineStoredPath);
                    prescriptionModel.setDoseImagePath(doseStoredPath);
                    prescriptionList.add(prescriptionModel);
                    prescriptionAdapter.notifyDataSetChanged();
                    drawer.closeDrawer(Gravity.START);
                    cMedicine.clear();
                    cDose.clear();
                    //Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    showToast("Please try again...");
                }
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,R.layout.row_auto_complete_text, arr);

        autoCompleteSearch.setThreshold(2);
        autoCompleteSearch.setAdapter(adapter);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivAddNew:
                drawer.openDrawer(Gravity.START);
                break;
            case R.id.ivSearch:
                drawer.openDrawer(Gravity.END);
                break;
            case R.id.btnAddMedicine:
                initAddMedicine();
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

        }
    }

    private void initAddTest() {
        String test = etTest.getText().toString().trim();
        if (test.isEmpty()){
            showToast("First enter a test.");
        }else{
            CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
            prescriptionModel.setType("TEST");
            prescriptionModel.setTest(test);
            prescriptionList.add(prescriptionModel);
            prescriptionAdapter.notifyDataSetChanged();
            etTest.setText("");
            drawer.closeDrawer(Gravity.END);
        }
    }

    private void initAddMedicine() {
        String medicine = autoCompleteSearch.getText().toString().trim();
        String dose = etDose.getText().toString().trim();
        if (medicine.isEmpty()){
            showToast("First enter a medicine name.");
        }else if (dose.isEmpty()){
            showToast("Please add dose.");
        }else{
            CreatePrescriptionModel prescriptionModel = new CreatePrescriptionModel();
            prescriptionModel.setType("TEXT");
            prescriptionModel.setMedicine(medicine);
            prescriptionModel.setDose(dose);
            prescriptionList.add(prescriptionModel);
            prescriptionAdapter.notifyDataSetChanged();
            autoCompleteSearch.setText("");
            etDose.setText("");
            drawer.closeDrawer(Gravity.END);
        }
    }

    private void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
