package com.example.singlecell.model;

import java.util.List;

public class TissueSunChart {

    private String name;

    private ItemStyle itemStyle;

    private List<ChildSunChart> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    public List<ChildSunChart> getChildren() {
        return children;
    }

    public void setChildren(List<ChildSunChart> children) {
        this.children = children;
    }
}
