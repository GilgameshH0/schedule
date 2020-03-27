package com.sky.vsuwt_schedule_.models;

public class profileModel {

    private String name;
    private String url;
    private boolean isChecked;
    private int indexT;

    public int getIndexT() {
        return indexT;
    }

    public void setIndexT(int indexT) {
        this.indexT = indexT;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
