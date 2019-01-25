package com.smartcold.manage.cold.entity;

import java.io.Serializable;
import java.util.Date;

/*
 * Copyright (C) DCIS 版权所有
 * 功能描述: ItemValue 卡片信息
 * Create on MaQiang 2018-11-3 20:48:14
 * @version 1.0.1
 */
public class ItemValue implements Serializable {

	private Double value;
	private Date time;//采集时间
	private Integer flage=0; // 运行状态
	private Integer status=0; // 0:正常值，1 能修复 2 不能修复
	private static final long serialVersionUID = -6385769548005386256L;
	
	
	public ItemValue() {
		super();
	}

	public ItemValue(Double value, Date time) {
		this.value = value;
		this.time = time;
	}

	public ItemValue(Double value, Date time, int flage) {
		this.value = value;
		this.time = time;
		this.flage = flage;
	}


	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
		this.status=1;
	}
	public void setValue(Double value,Date time) {
		this.value = value;
		this.time=time;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFlage() {
		return flage;
	}

	public void setFlage(Integer flage) {
		this.flage = flage;
	}

	@Override
	public String toString() {
		return "ItemValue{" +
				"value=" + value +
				", time=" + time +
				'}';
	}


}
