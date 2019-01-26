package com.smartcold.manage.cold.controller.para;

import com.google.gson.Gson;
import com.smartcold.manage.cold.entity.para.ParaDo;
import com.smartcold.manage.cold.service.para.ParameterService;
import com.smartcold.manage.cold.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 参数配置保存
     * @return
     */
    @PostMapping(value = "/setPara")
    public R savePara(ParaDo paraDo) {
        try {
            if (paraDo.getPmid() == null) {
                return R.newFailure("参数为空");
            }
//            String jsonmap = new Gson().toJson(paraDo.getMapping());
            System.out.println("转化后的map");
//            paraDo.setMapping(jsonmap);
            if (paraDo.getId() != null) {
                // 修改
                if (parameterService.update(paraDo)) {
                    return R.newSuccess();
                }
            }else{
                // 添加
                if (parameterService.save(paraDo)) {
                    return R.newSuccess();
                }
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
    @PostMapping(value = "removePara")
    public R deletePara(ParaDo paraDo) {
        if (parameterService.delete(paraDo.getId())) {
            return R.newSuccess();
        }
        return R.newFailure();
    }
}
