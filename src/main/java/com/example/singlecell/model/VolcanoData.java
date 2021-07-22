package com.example.singlecell.model;

public class VolcanoData {

    private int id;

    private String gene;

    private String logfc;

    private String pvalue;

    private String status;

    private String yintercept;

    private String xinterceptleft;

    private String xinterceptright;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getLogfc() {
        return logfc;
    }

    public void setLogfc(String logfc) {
        this.logfc = logfc;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYintercept() {
        return yintercept;
    }

    public void setYintercept(String yintercept) {
        this.yintercept = yintercept;
    }

    public String getXinterceptleft() {
        return xinterceptleft;
    }

    public void setXinterceptleft(String xinterceptleft) {
        this.xinterceptleft = xinterceptleft;
    }

    public String getXinterceptright() {
        return xinterceptright;
    }

    public void setXinterceptright(String xinterceptright) {
        this.xinterceptright = xinterceptright;
    }
}
