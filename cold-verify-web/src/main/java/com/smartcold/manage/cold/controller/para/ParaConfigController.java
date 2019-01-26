package com.smartcold.manage.cold.controller.para;

import com.google.gson.Gson;
import com.smartcold.manage.cold.entity.para.LayDo;
import com.smartcold.manage.cold.entity.para.ParaDo;
import com.smartcold.manage.cold.service.para.LayoutService;
import com.smartcold.manage.cold.service.para.ParameterService;
import com.smartcold.manage.cold.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Copyright (C), 2015-2019,
 * FileName: ParaConfigController
 * Author:   yigang
 * Date:     2019/1/25 17:05
 * Description: 参数配置控制层
 * History:V1.0.0
 */
@RestController
@RequestMapping("/para")
public class ParaConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ParaConfigController.class);

    @Autowired
    private ParameterService parameterService;
    @Autowired
    private LayoutService layoutService;

    /**
     * 初始化页面获取参数
     * @param paraDo
     * @return
     */
    @PostMapping(value = "/getPara")
    public R getPara(@RequestBody ParaDo paraDo) {
        if (paraDo.getPmid() == null) {
            return R.newFailure("参数有误");
        }
        try {
            R r = new R();
            ParaDo paraDemo = parameterService.getbypara(paraDo.getPmid());
            if (paraDemo.getMapping() != null && !"".equals(paraDemo.getMapping())) {
                r.put("paramod", paraDemo.getMapping());
            }
            LayDo layDo = layoutService.getbypara(paraDo.getPmid());
            if (layDo != null) {
                r.put("layouts", layDo.getLayMapping());
            }
            // 获取布点方案
            List<LayDo> lis = layoutService.getbyspareproId(paraDo.getPmid());
            if (lis != null) {
                r.put("lisLay", lis);
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.newFailure();
    }

    /**
     * 参数配置保存
     * @return
     */
    @PostMapping(value = "/setPara")
    public R savePara(@RequestBody ParaDo paraDo) {
        try {
            if (paraDo.getPmid() == null) {
                return R.newFailure("参数为空");
            }
            System.out.println("转化后的map");
            if (paraDo.getId() != null) {
                // 修改
                if (parameterService.update(paraDo)) {
                    if (layoutService.update(new LayDo(paraDo.getPmid(), paraDo.getLayMapping()))) {
                        return R.newSuccess();
                    }
                }
            }else{
                // 添加
                if (parameterService.save(paraDo)) {
                    if (layoutService.save(new LayDo(paraDo.getPmid(), paraDo.getLayMapping()))) {
                        return R.newSuccess();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.newFailure();
    }

    /**
     * 根据id查询布点方案信息
     * @param layId
     * @return
     */
    @PostMapping(value = "/getlayout")
    public R getlayout(@RequestBody Integer layId) {
        try {
            if (layId != null) {
                LayDo layDo = layoutService.get(layId);
                R r = new R();
                r.put("laymod", layDo);
                return r;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.newFailure();
    }


    /**
     * 删除配置信息
     * @param paraDo
     * @return
     */
    @PostMapping(value = "/removePara")
    public R deletePara(ParaDo paraDo) {
        if (parameterService.delete(paraDo.getId())) {
            return R.newSuccess();
        }
        return R.newFailure();
    }
}
