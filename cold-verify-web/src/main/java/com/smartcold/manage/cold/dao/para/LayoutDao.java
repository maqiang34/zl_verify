package com.smartcold.manage.cold.dao.para;

import com.smartcold.manage.cold.entity.para.LayDo;
import com.smartcold.manage.cold.entity.para.ParaDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LayoutDao {
    LayDo get(Integer id);

    LayDo getbyproId(Integer proId);

    List<LayDo> getbyspareproId(Integer proId);

    int save(LayDo layDo);

    int update(LayDo layDo);

    int remove(Integer proId);

    int removebyProId(Integer proId);
}
