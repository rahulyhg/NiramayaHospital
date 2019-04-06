
package com.infobite.niramayahospital.models.pharmay_modal.items_data_modal.select_medicine_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medcinie implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("costing")
    @Expose
    private String costing;
    @SerializedName("selling_price")
    @Expose
    private String sellingPrice;
    @SerializedName("store_box")
    @Expose
    private String storeBox;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("generic_name")
    @Expose
    private String genericName;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("side_effect")
    @Expose
    private String sideEffect;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("recommendation")
    @Expose
    private String recommendation;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    public final static Parcelable.Creator<Medcinie> CREATOR = new Creator<Medcinie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Medcinie createFromParcel(Parcel in) {
            return new Medcinie(in);
        }

        public Medcinie[] newArray(int size) {
            return (new Medcinie[size]);
        }

    };

    protected Medcinie(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.costing = ((String) in.readValue((String.class.getClassLoader())));
        this.sellingPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.storeBox = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        this.genericName = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
        this.sideEffect = ((String) in.readValue((String.class.getClassLoader())));
        this.availability = ((String) in.readValue((String.class.getClassLoader())));
        this.recommendation = ((String) in.readValue((String.class.getClassLoader())));
        this.discount = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Medcinie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCosting() {
        return costing;
    }

    public void setCosting(String costing) {
        this.costing = costing;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getStoreBox() {
        return storeBox;
    }

    public void setStoreBox(String storeBox) {
        this.storeBox = storeBox;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(image);
        dest.writeValue(categoryId);
        dest.writeValue(costing);
        dest.writeValue(sellingPrice);
        dest.writeValue(storeBox);
        dest.writeValue(quantity);
        dest.writeValue(genericName);
        dest.writeValue(companyName);
        dest.writeValue(sideEffect);
        dest.writeValue(availability);
        dest.writeValue(recommendation);
        dest.writeValue(discount);
        dest.writeValue(status);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return 0;
    }
}
