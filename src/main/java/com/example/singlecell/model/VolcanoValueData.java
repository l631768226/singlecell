package com.example.singlecell.model;

import java.util.List;

/**
 * 火山图散点图数据
 */
public class VolcanoValueData {

    private List<String> value;

    private ItemStyle itemStyle = new ItemStyle();

    public VolcanoValueData(String color){
        Normal normal = new Normal();
        normal.setColor(color);
        itemStyle.setNormal(normal);
    }


    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}
