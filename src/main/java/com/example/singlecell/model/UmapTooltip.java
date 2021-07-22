package com.example.singlecell.model;

import java.util.Arrays;
import java.util.List;

public class UmapTooltip {

    private List<Integer> tooltip = Arrays.asList(0, 1, 2);;

    public List<Integer> getTooltip() {
        return tooltip;
    }

    public void setTooltip(List<Integer> tooltip) {
        this.tooltip = tooltip;
    }
}
