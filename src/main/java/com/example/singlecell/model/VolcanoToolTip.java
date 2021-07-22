package com.example.singlecell.model;

import java.util.Arrays;
import java.util.List;

public class VolcanoToolTip {

    private List<Integer> tooltip = Arrays.asList(2, 0, 1, 3);;

    public List<Integer> getTooltip() {
        return tooltip;
    }

    public void setTooltip(List<Integer> tooltip) {
        this.tooltip = tooltip;
    }

}
