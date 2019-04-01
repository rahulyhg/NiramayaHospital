package com.infobite.niramayahospital.models.pharmay_modal.items_data_modal;

public class ItemsDataModal {
    private String name;
    private String description;
    private double unitCost;
    private double quantity;
    private double discount;
    private double tax;
    private double totalAmount;

    public ItemsDataModal(String name, String description, double unitCost, double quantity, double discount, double tax, double totalAmount) {
        this.name = name;
        this.description = description;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.discount = discount;
        this.tax = tax;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
