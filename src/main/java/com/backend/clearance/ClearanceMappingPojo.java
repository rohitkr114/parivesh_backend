package com.backend.clearance;

public class ClearanceMappingPojo {
    String form;
    String categoryType;
    String categoryName;

    public String getForm() {
        return form;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public String getCategoryType() {
        return categoryType;
    }
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ClearanceMappingPojo(String form, String categoryType, String categoryName ) {
        this.form = form;
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }
}
