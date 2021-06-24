package com.example.singlecell.model;

public class Description {

    private int id;

    private String datasetname;

    private String databaseid;

    private String accession;

    private String tissue;

    private String sample;

    private String groupdata;

    private String cellsnum;

    private String celltype;

    private String sourcedata;

    private String description;

    private String publication;

    private String pubweb;

    public String getDatabaseid() {
        return databaseid;
    }

    public void setDatabaseid(String databaseid) {
        this.databaseid = databaseid;
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

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPubweb() {
        return pubweb;
    }

    public void setPubweb(String pubweb) {
        this.pubweb = pubweb;
    }

    public String getGroupdata() {
        return groupdata;
    }

    public void setGroupdata(String groupdata) {
        this.groupdata = groupdata;
    }

    public String getSourcedata() {
        return sourcedata;
    }

    public void setSourcedata(String sourcedata) {
        this.sourcedata = sourcedata;
    }
}
