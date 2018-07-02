package com.rain.model.vo.bill;

/**
 * Created by Administrator on 2018-6-25 0025.
 */
public class BillOrder {
    Long ID;
    String company;
    String MLPRICE;
    String NPRICE;
    String GPRICE;
    String AMLPRICE;
    String TYPE;
    Double MINCONSUM;
    String billOrder;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMLPRICE() {
        return MLPRICE;
    }

    public void setMLPRICE(String MLPRICE) {
        this.MLPRICE = MLPRICE;
    }

    public String getNPRICE() {
        return NPRICE;
    }

    public void setNPRICE(String NPRICE) {
        this.NPRICE = NPRICE;
    }

    public String getGPRICE() {
        return GPRICE;
    }

    public void setGPRICE(String GPRICE) {
        this.GPRICE = GPRICE;
    }

    public String getAMLPRICE() {
        return AMLPRICE;
    }

    public void setAMLPRICE(String AMLPRICE) {
        this.AMLPRICE = AMLPRICE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public Double getMINCONSUM() {
        return MINCONSUM;
    }

    public void setMINCONSUM(Double MINCONSUM) {
        this.MINCONSUM = MINCONSUM;
    }

    public String getBillOrder() {
        return billOrder;
    }

    public void setBillOrder(String billOrder) {
        this.billOrder = billOrder;
    }
}
