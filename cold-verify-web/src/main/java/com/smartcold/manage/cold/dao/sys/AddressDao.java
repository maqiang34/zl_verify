package com.smartcold.manage.cold.dao.sys;


import com.smartcold.manage.cold.entity.sys.GoodsAddressDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressDao {
    GoodsAddressDo get(Long id);

    List<GoodsAddressDo> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GoodsAddressDo addressDo);

    int update(GoodsAddressDo addressDo);

    int updateother(GoodsAddressDo addressDo);

    int remove(Long id);

    int bindUser(GoodsAddressDo addressDo);
}
