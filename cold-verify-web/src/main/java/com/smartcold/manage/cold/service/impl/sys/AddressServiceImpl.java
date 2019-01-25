package com.smartcold.manage.cold.service.impl.sys;

import com.smartcold.manage.cold.dao.sys.AddressDao;
import com.smartcold.manage.cold.entity.sys.GoodsAddressDo;
import com.smartcold.manage.cold.entity.sys.UserDO;
import com.smartcold.manage.cold.service.sys.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2015-2018,
 * FileName: AddressServiceImpl
 * Author:   yigang
 * Date:     2018/11/3 11:30
 * Description: 收货地址业务处理层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;


    @Override
    public GoodsAddressDo get(Long id) {
        return addressDao.get(id);
    }

    @Override
    public List<GoodsAddressDo> list(Map<String, Object> map) {
        return addressDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return addressDao.count(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(GoodsAddressDo addressDo, HttpServletRequest request) {
        // 保存地址
        if (addressDao.save(addressDo) >0) {
            UserDO userDO = (UserDO) request.getSession().getAttribute("userLogin");
//            UserDO userDO = (UserDO) SecurityUtils.getSubject().getSession().getAttribute("userLogin");
            addressDo.setUserId(userDO.getUserId());
            addressDo.setAddressId(addressDo.getId());
            // 绑定地址到用户
            addressDao.bindUser(addressDo);
            if (addressDo.getDefaultIs() != null) {
                Long i = Long.valueOf(1);
                addressDo.setDefaultIs(i);
                // 修改其他地址--不为默认
                addressDao.updateother(addressDo);
            }
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int bindUser(GoodsAddressDo addressDo) {
        return addressDao.bindUser(addressDo);
    }

    @Override
    public int update(GoodsAddressDo addressDo) {
        if (addressDao.update(addressDo) > 0) {
            if (addressDo.getDefaultIs() !=null) {
                Long i = Long.valueOf(1);
                addressDo.setDefaultIs(i);
                // 替换其他默认地址
                addressDao.updateother(addressDo);
            }
            return 1;
        }
        return 0;
    }



    @Override
    public int remove(Long id) {
        return addressDao.remove(id);
    }
}
