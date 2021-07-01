package com.example.singlecell.model;

public class CtmDownloadList {

    private int id;

    private String datasetname;

    private String databaseid;

    private String accession;

    private String accessionHtml;

    private String tissue;

    private String sample;

    private String groupdata;

    private String cellsnum;

    private String celltype;

    private String sourcedata;

    private String profile;

    private String alldegs;

    public String getAlldegs(){
        return alldegs;
    }

    public void setAlldegs(String alldegs){
        this.alldegs = alldegs;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatasetname() {
        return datasetname;
    }

    public void setDatasetname(String datasetname) {
        this.datasetname = datasetname;
    }

    public String getDatabaseid() {
        return databaseid;
    }

    public void setDatabaseid(String databaseid) {
        this.databaseid = databaseid;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getAccessionHtml() {
        return accessionHtml;
    }

    public void setAccessionHtml(String accessionHtml) {
        this.accessionHtml = accessionHtml;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getGroupdata() {
        return groupdata;
    }

    public void setGroupdata(String groupdata) {
        this.groupdata = groupdata;
    }

    public String getCellsnum() {
        return cellsnum;
    }

    public void setCellsnum(String cellsnum) {
        this.cellsnum = cellsnum;
    }

    public String getCelltype() {
        return celltype;
    }

    public void setCelltype(String celltype) {
        this.celltype = celltype;
    }

    public String getSourcedata() {
        return sourcedata;
    }

    public void setSourcedata(String sourcedata) {
        this.sourcedata = sourcedata;
    }

}
