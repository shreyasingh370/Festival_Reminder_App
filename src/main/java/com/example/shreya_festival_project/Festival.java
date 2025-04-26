package com.example.shreya_festival_project;

public class Festival {
    private String name;
    private String date;
    private String cultureInfo;

    public Festival(String name, String date, String cultureInfo) {
        this.name = name;
        this.date = date;
        this.cultureInfo = cultureInfo;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCultureInfo() {
        return cultureInfo;
    }
}
