package com.example.singlecell.model;

import java.util.List;

public class CtmBrowseRst {

    private int id;

    private String label;

    private List<CtmChild> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CtmChild> getChildren() {
        return children;
    }

    public void setChildren(List<CtmChild> children) {
        this.children = children;
    }
}
