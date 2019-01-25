package com.smartcold.manage.cold.entity;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @Description: 基类
 * @createDate:2018-08-28 17:00
 * @version:V1.0
 */
public class VerifyDataEntity {

    private HashMap<Integer,Integer[]> errDataInfo = new HashMap<>();//数据错误
    // 卡片信息-->key:CARIDS,NUMS,SNS,POINS
    HashMap<String,LinkedList<String> > cuspcMap=new HashMap<>();
    // 存放编号作为下标时间，温度参数
    private HashMap<Integer, LinkedList<ItemValue>> dataMap = new HashMap<>();
    /**
     *
     * @param cuspcMap:CARIDS,NUMS,SNS,POINS,userCardid
     * @param dataMap
     */
    public VerifyDataEntity( HashMap<String,LinkedList<String> > cuspcMap, HashMap<Integer, LinkedList<ItemValue>> dataMap) {
        this.cuspcMap=cuspcMap;
        this.dataMap = dataMap;
    }

    public HashMap<Integer, Integer[]> getErrDataInfo() {
        return errDataInfo;
    }

    public void setErrDataInfo(HashMap<Integer, Integer[]> errDataInfo) {
        this.errDataInfo = errDataInfo;
    }

    public HashMap<String, LinkedList<String>> getCuspcMap() {
        return cuspcMap;
    }

    public void setCuspcMap(HashMap<String, LinkedList<String>> cuspcMap) {
        this.cuspcMap = cuspcMap;
    }

    public HashMap<Integer, LinkedList<ItemValue>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<Integer, LinkedList<ItemValue>> dataMap) {
        this.dataMap = dataMap;
    }

    public void addDataMap(Integer carid, Integer[] errinfo, LinkedList<ItemValue> dataList) {
        this.errDataInfo.put(carid,errinfo);
        this.dataMap.put(carid,dataList);
    }


}
