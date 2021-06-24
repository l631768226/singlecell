package com.example.singlecell.model;

import java.util.List;

public class CtmBrowseRst {

    private String id;

    private String label;

    private List<CtmChild> children;

    private boolean show = false;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
