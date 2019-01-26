package com.smartcold.manage.cold.entity.para;

/**
 * Copyright (C), 2015-2019,
 * FileName: LayDo
 * Author:   yigang
 * Date:     2019/1/26 19:41
 * Description:
 * History:V1.0.0
 */
public class LayDo {
    private Integer id;
    private Integer pmid;
    private String layMapping;
    private Integer sparePid;
    private String name;

    public LayDo(Integer pmid, String layMapping) {
        this.pmid = pmid;
        this.layMapping = layMapping;
    }

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

    public String getLayMapping() {
        return layMapping;
    }

    public void setLayMapping(String layMapping) {
        this.layMapping = layMapping;
    }

    public Integer getSparePid() {
        return sparePid;
    }

    public void setSparePid(Integer sparePid) {
        this.sparePid = sparePid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
