package com.smartcold.manage.cold.service.para;


import com.smartcold.manage.cold.entity.para.ParaDo;

public interface ParameterService {

    ParaDo getbypara(Integer proId);

    ParaDo get(Integer id);

    boolean save(ParaDo paraDo);

    boolean saveform(ParaDo paraDo, Integer proId);

    ParaDo loadfile(ParaDo paraDo, Integer proId);

    boolean update(ParaDo paraDo);

    boolean delete(Integer id);
}
