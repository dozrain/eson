package com.rain.model.vo.bill;

/**
 * Created by Administrator on 2018-6-22 0022.
 */
public class Bill {
    String  zhtdat;
    String  zbjsta;
    String  matnr;
    Double  taxCost;
    Double  bestBill;
    String  formula;
    String  status;

    public String getZhtdat() {
        return zhtdat;
    }

    public void setZhtdat(String zhtdat) {
        this.zhtdat = zhtdat;
    }

    public String getZbjsta() {
        return zbjsta;
    }

    public void setZbjsta(String zbjsta) {
        this.zbjsta = zbjsta;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public Double getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(Double taxCost) {
        this.taxCost = taxCost;
    }

    public Double getBestBill() {
        return bestBill;
    }

    public void setBestBill(Double bestBill) {
        this.bestBill = bestBill;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
