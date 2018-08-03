package com.sml.mgoode.entity;

/**
 * Created by michaelgoode on 02/03/2018.
 */
public class ProductEntry {

    long id;
    long lookupId;
    String UValue1;
    String UValue2;
    String UValue3;
    String CValue1;
    String CValue2;
    String CValue3;
    String createdDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUValue1() {
        return UValue1;
    }

    public void setUValue1(String UValue1) {
        this.UValue1 = UValue1;
    }

    public String getUValue2() {
        return UValue2;
    }

    public void setUValue2(String UValue2) {
        this.UValue2 = UValue2;
    }

    public String getUValue3() {
        return UValue3;
    }

    public void setUValue3(String VUvalue3) {
        this.UValue3 = VUvalue3;
    }

    public String getCValue1() {
        return CValue1;
    }

    public void setCValue1(String CValue1) {
        this.CValue1 = CValue1;
    }

    public String getCValue2() {
        return CValue2;
    }

    public void setCValue2(String CValue2) {
        this.CValue2 = CValue2;
    }

    public String getCValue3() {
        return CValue3;
    }

    public void setCValue3(String CValue3) {
        this.CValue3 = CValue3;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public long getLookupId() {
        return lookupId;
    }

    public void setLookupId(long lookupId) {
        this.lookupId = lookupId;
    }
}
