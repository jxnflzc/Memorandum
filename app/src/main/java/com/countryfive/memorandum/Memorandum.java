package com.countryfive.memorandum;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Memorandum extends DataSupport{
    private int id;
    private String memorandumName;
    private String memorandumInfo;
    private String saveDate;
    private String saveTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemorandumName() {
        return memorandumName;
    }

    public void setMemorandumName(String memorandumName) {
        this.memorandumName = memorandumName;
    }

    public String getMemorandumInfo() {
        return memorandumInfo;
    }

    public void setMemorandumInfo(String memorandumInfo) {
        this.memorandumInfo = memorandumInfo;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }
}
