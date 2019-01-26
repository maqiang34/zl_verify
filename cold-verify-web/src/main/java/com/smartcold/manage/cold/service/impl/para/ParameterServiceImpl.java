package com.smartcold.manage.cold.service.impl.para;

import com.smartcold.manage.cold.dao.para.ParameterDao;
import com.smartcold.manage.cold.entity.para.ParaDo;
import com.smartcold.manage.cold.service.para.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2015-2019,
 * FileName: ParameterServiceImpl
 * Author:   yigang
 * Date:     2019/1/25 18:21
 * Description:
 * History:V1.0.0
 */
@Service
public class ParameterServiceImpl implements ParameterService {
    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(ParameterServiceImpl.class);

    @Autowired
    private ParameterDao parameterDao;


    @Override
    public ParaDo getbypara(Integer proId) {
        return parameterDao.getbyproId(proId);
    }

    @Override
    public ParaDo get(Integer id) {
        return parameterDao.get(id);
    }

    @Override
    public boolean save(ParaDo paraDo) {
        if (parameterDao.save(paraDo) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveform(ParaDo paraDo, Integer proId) {
        return false;
    }

    @Override
    public ParaDo loadfile(ParaDo paraDo, Integer proId) {
        return null;
    }

    @Override
    public boolean update(ParaDo paraDo) {
        if (parameterDao.update(paraDo) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (parameterDao.remove(id) > 0) {
            return true;
        }
        return false;
    }
}
