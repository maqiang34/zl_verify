package com.smartcold.manage.cold.entity.sys;

/**
 * Copyright (C), 2015-2018,
 * FileName: GoodsAddressDo
 * Author:   yigang
 * Date:     2018/11/3 10:48
 * Description: 收货地址
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class GoodsAddressDo {
    private Long id;
    // 收货人
    private String consignee;
    // 手机号
    private String phone;
    // 收货详细地址
    private String address;
    // 省份
    private String province;
    // 所在城市
    private String city;
    // 所在地区
    private String district;
    // 是否设置为默认收货地址
    private Long defaultIs;
    // 用户名
    private String name;

    // 用户id
    private Long userId;
    // 地址id
    private Long addressId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getDefaultIs() {
        return defaultIs;
    }

    public void setDefaultIs(Long defaultIs) {
        this.defaultIs = defaultIs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "GoodsAddressDo{" +
                "id=" + id +
                ", consignee='" + consignee + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", defaultIs=" + defaultIs +
                ", name='" + name + '\'' +
                '}';
    }
}
