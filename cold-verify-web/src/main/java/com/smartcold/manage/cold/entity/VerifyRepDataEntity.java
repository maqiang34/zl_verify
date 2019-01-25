package com.smartcold.manage.cold.entity;

import java.util.LinkedList;

/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @Description: 基类
 * @createDate:2018-08-28 17:00
 * @version:V1.0
 */
public class VerifyRepDataEntity {
    private Integer carId;
    private Integer [] errInfo;////错误总数 , 时间缺失总记录 , 修复的数量 , 未修复的数量
    private LinkedList<ItemValue> list;// 数

    public VerifyRepDataEntity(Integer carId, Integer [] errInfo, LinkedList<ItemValue> list) {
        this.carId = carId;
        this.errInfo = errInfo;
        this.list = list;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer[] getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(Integer[] errInfo) {
        this.errInfo = errInfo;
    }

    public LinkedList<ItemValue> getList() {
        return list;
    }

    public void setList(LinkedList<ItemValue> list) {
        this.list = list;
    }
}
