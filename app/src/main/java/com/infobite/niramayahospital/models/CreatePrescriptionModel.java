package com.infobite.niramayahospital.models;

public class CreatePrescriptionModel {

    private String type;
    private String id;
    private String medicineImagePath;
    private String doseImagePath;
    private String medicine;
    private String dose;
    private String test;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicineImagePath() {
        return medicineImagePath;
    }

    public void setMedicineImagePath(String medicineImagePath) {
        this.medicineImagePath = medicineImagePath;
    }

    public String getDoseImagePath() {
        return doseImagePath;
    }

    public void setDoseImagePath(String doseImagePath) {
        this.doseImagePath = doseImagePath;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
