package com.smartcold.manage.cold.entity;

/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @ClassName InfluxdbConf.java
 * @Description: Influxdb配置类
 * @createDate:2018-02-28 16:47:46
 * @version:V1.0
 */

public class InfluxdbConf {

    private String url;
    private String username;
    private String password;
    private String database;
    private String measurements;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }
}
