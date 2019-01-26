package com.smartcold.manage.cold.service.impl.para;

import com.smartcold.manage.cold.dao.para.LayoutDao;
import com.smartcold.manage.cold.entity.para.LayDo;
import com.smartcold.manage.cold.service.para.LayoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2015-2019,
 * FileName: ParameterServiceImpl
 * Author:   yigang
 * Date:     2019/1/25 18:21
 * Description:
 * History:V1.0.0
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LayoutServiceImpl implements LayoutService {
    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(LayoutServiceImpl.class);

    @Autowired
    private LayoutDao layoutDao;


    @Override
    public LayDo getbypara(Integer proId) {
        return layoutDao.getbyproId(proId);
    }

    @Override
    public List<LayDo> getbyspareproId(Integer proId) {
        return layoutDao.getbyspareproId(proId);
    }

    @Override
    public LayDo get(Integer id) {
        return layoutDao.get(id);
    }

    @Override
    public boolean save(LayDo layDo) {
        if (layoutDao.save(layDo) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveform(LayDo layDo, Integer proId) {
        return false;
    }

    @Override
    public LayDo loadfile(LayDo layDo, Integer proId) {
        return null;
    }

    @Override
    public boolean update(LayDo paraDo) {
        if (layoutDao.update(paraDo) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer proId) {
        if (layoutDao.remove(proId) > 0) {
            return true;
        }
        return false;
    }


}
