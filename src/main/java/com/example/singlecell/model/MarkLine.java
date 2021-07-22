package com.example.singlecell.model;

import java.util.Arrays;
import java.util.List;

public class MarkLine {

    private List<String> symbol = Arrays.asList("none", "none");

    private Boolean animation = false;

    private Boolean silent = true;

    private LineNormal lineStyle = new LineNormal();

    private List<Axis> data;

    private MarkLineLabel label = new MarkLineLabel();

    public MarkLineLabel getLabel() {
        return label;
    }

    public void setLabel(MarkLineLabel label) {
        this.label = label;
    }

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    public List<String> getSymbol() {
        return symbol;
    }

    public void setSymbol(List<String> symbol) {
        this.symbol = symbol;
    }

    public Boolean getSilent() {
        return silent;
    }

    public void setSilent(Boolean silent) {
        this.silent = silent;
    }

    public LineNormal getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(LineNormal lineStyle) {
        this.lineStyle = lineStyle;
    }

    public List<Axis> getData() {
        return data;
    }

    public void setData(List<Axis> data) {
        this.data = data;
    }
}
