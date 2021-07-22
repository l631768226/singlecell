package com.example.singlecell.model;

public class GoDown {

    private int id;

    private String ontology;

    private String goid;

    private String description;

    private Double pvalue;

    private Double logpvalue;

    private String geneid;

    private String status;

    private String celltype;

    private String dataset;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    public String getGoid() {
        return goid;
    }

    public void setGoid(String goid) {
        this.goid = goid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getPvalue() {
////        return pvalue;
////    }
////
////    public void setPvalue(String pvalue) {
////        this.pvalue = pvalue;
////    }


    public Double getPvalue() {
        return pvalue;
    }

    public void setPvalue(Double pvalue) {
        this.pvalue = pvalue;
    }

    public Double getLogpvalue() {
        return logpvalue;
    }

    public void setLogpvalue(Double logpvalue) {
        this.logpvalue = logpvalue;
    }

    public String getGeneid() {
        return geneid;
    }

    public void setGeneid(String geneid) {
        this.geneid = geneid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCelltype() {
        return celltype;
    }

    public void setCelltype(String celltype) {
        this.celltype = celltype;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }
}
