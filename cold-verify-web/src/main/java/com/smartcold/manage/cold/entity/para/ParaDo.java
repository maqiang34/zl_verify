package com.smartcold.manage.cold.entity.para;

/**
 * Copyright (C), 2015-2019,
 * FileName: ParaDo
 * Author:   yigang
 * Date:     2019/1/25 17:49
 * Description: 参数配置实体类
 * History:V1.0.0
 */
public class ParaDo {
    private Integer id;
    private Integer pmid;
    private Integer lyid;
    private String mapping;
    private String layMapping;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public Integer getLyid() {
        return lyid;
    }

    public void setLyid(Integer lyid) {
        this.lyid = lyid;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getLayMapping() {
        return layMapping;
    }

    public void setLayMapping(String layMapping) {
        this.layMapping = layMapping;
    }

    @Override
    public String toString() {
        return "ParaDo{" +
                "id=" + id +
                ", pmid=" + pmid +
                ", lyid=" + lyid +
                ", mapping='" + mapping + '\'' +
                '}';
    }
}
