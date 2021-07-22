package com.example.singlecell.model;

public class UmapData {

    private int id;

    private String cell;

    private String umapx;

    private String umapy;

    private String celltype;

    private String dataset;

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getUmapx() {
        return umapx;
    }

    public void setUmapx(String umapx) {
        this.umapx = umapx;
    }

    public String getUmapy() {
        return umapy;
    }

    public void setUmapy(String umapy) {
        this.umapy = umapy;
    }

    public String getCelltype() {
        return celltype;
    }

    public void setCelltype(String celltype) {
        this.celltype = celltype;
    }
}
