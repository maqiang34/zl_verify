package com.smartcold.manage.cold.dao.para;

import com.smartcold.manage.cold.entity.para.ParaDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParameterDao {
    ParaDo get(Integer id);

    ParaDo getbyproId(Integer proId);

    int save(ParaDo paraDo);

    int update(ParaDo paraDo);

    int remove(Integer id);
}
