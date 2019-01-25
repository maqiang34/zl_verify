package com.smartcold.manage.cold.service.sys;



import com.smartcold.manage.cold.entity.sys.GoodsAddressDo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AddressService {

    GoodsAddressDo get(Long id);

    List<GoodsAddressDo> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GoodsAddressDo addressDo, HttpServletRequest request);

    int bindUser(GoodsAddressDo addressDo);

    int update(GoodsAddressDo addressDo);

    int remove(Long id);


}
