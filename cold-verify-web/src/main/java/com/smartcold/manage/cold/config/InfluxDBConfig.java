package com.smartcold.manage.cold.config;

import okhttp3.OkHttpClient.Builder;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @Description:动态配置influxdb 数据源
 * 支持多数据源配置
 * @createDate:2018-11-09 16:20
 * @version:V1.0
 */
@Configuration
public class InfluxDBConfig implements Serializable {
    public static Integer BLOCKSIZE;
    public static String URL, USERNAME, PASSWORD, DATABASE;
    private  static Long CONNECTTIMEOUT, READTIMEOUT, WRITETIMEOUT;
    private static Builder builder =null;
    protected final Logger logger = LoggerFactory.getLogger(InfluxDBConfig.class);


    public InfluxDBConfig(@Value("${influxdb.username}") String username, @Value("${influxdb.password}") String password,
                          @Value("${influxdb.database}") String database, @Value("${influxdb.blocksize}") Integer blocksize,
                          @Value("${influxdb.url}") String url, @Value("${influxdb.connect-timeout}")  Long connecttimeout,
                          @Value("${influxdb.read-timeout}")Long readtimeout, @Value("${influxdb.write-timeout}")Long writetimeout ) {
        InfluxDBConfig.USERNAME = username;
        InfluxDBConfig.PASSWORD = password;
        InfluxDBConfig.DATABASE = database;
        InfluxDBConfig.BLOCKSIZE = blocksize;
        InfluxDBConfig.URL = url;
        InfluxDBConfig.CONNECTTIMEOUT = connecttimeout;
        InfluxDBConfig.READTIMEOUT = readtimeout;
        InfluxDBConfig.WRITETIMEOUT = writetimeout;
        InfluxDBConfig.builder = new Builder().connectTimeout(InfluxDBConfig.CONNECTTIMEOUT, TimeUnit.SECONDS).readTimeout(READTIMEOUT, TimeUnit.SECONDS).writeTimeout(InfluxDBConfig.READTIMEOUT, TimeUnit.SECONDS);
    }

    public static InfluxDB getInfluxDB() {
        return InfluxDBFactory.connect(InfluxDBConfig.URL, InfluxDBConfig.USERNAME, InfluxDBConfig.PASSWORD, InfluxDBConfig.builder).setDatabase(InfluxDBConfig.DATABASE).enableBatch().enableGzip();
    }


}
