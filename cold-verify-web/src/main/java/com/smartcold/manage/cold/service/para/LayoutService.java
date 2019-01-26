package com.smartcold.manage.cold.service.para;


import com.smartcold.manage.cold.entity.para.LayDo;

import java.util.List;

public interface LayoutService {

    LayDo getbypara(Integer proId);

    List<LayDo> getbyspareproId(Integer proId);

    LayDo get(Integer id);

    boolean save(LayDo layDo);

    boolean saveform(LayDo layDo, Integer proId);

    LayDo loadfile(LayDo layDo, Integer proId);

    boolean update(LayDo layDo);

    boolean delete(Integer proId);


}
