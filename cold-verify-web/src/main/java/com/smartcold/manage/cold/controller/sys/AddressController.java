package com.smartcold.manage.cold.controller.sys;

import com.smartcold.manage.cold.controller.BaseController;
import com.smartcold.manage.cold.entity.sys.GoodsAddressDo;
import com.smartcold.manage.cold.entity.sys.UserDO;
import com.smartcold.manage.cold.service.sys.AddressService;
import com.smartcold.manage.cold.util.PageUtils;
import com.smartcold.manage.cold.util.Query;
import com.smartcold.manage.cold.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2015-2018,
 * FileName: AddressController
 * Author:   yigang
 * Date:     2018/11/3 11:46
 * Description: 收货地址控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RequestMapping("/sys/myaddress")
@Controller
public class AddressController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private String prefix="system/user"  ;

    @Autowired
    private AddressService addressService;




//    @RequiresPermissions("sys:myaddress:myaddress")
    @GetMapping("")
    String user(Model model) {
        logger.info("正在跳转页面");
        return prefix + "/myaddress";
    }


    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        // 查询所有地址数据
        PageUtils pageUtil = null;
        logger.info("查询收货地址数据===========");
        try {
            Query query = new Query(params);
            UserDO userDO = (UserDO) request.getSession().getAttribute("userLogin");
            // 获取用户id
//            UserDO userDO = (UserDO) SecurityUtils.getSubject().getSession().getAttribute("userLogin");
            logger.info("获取用户的登录信息：" + userDO);
            query.put("userId", userDO.getUserId());
            logger.info("Query:"+query.toString());
            String consignee = (String) query.get("consignee");
            if (consignee != null && !"".equals(consignee)) {
                query.put("consignee", "%" + consignee + "%");
            }
            List<GoodsAddressDo> list = addressService.list(query);
            int total = addressService.count(query);
            pageUtil = new PageUtils(list, total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageUtil;
    }


//    @RequiresPermissions("sys:myaddress:add")
    @GetMapping("/add")
    String add(Model model) {
        return prefix + "/addressadd";
    }

//    @RequiresPermissions("sys:myaddress:add")
    @PostMapping("/save")
    @ResponseBody
    R save(GoodsAddressDo goodsAddressDo, HttpServletRequest request) {
        logger.info("========添加用户中....");
        logger.info("需要添加的参数是：" + goodsAddressDo.toString());
        try {
            if (addressService.save(goodsAddressDo, request) > 0) {
                return R.newSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            R.newFailure("保存出错，请联系管理员");
        }
        return R.newFailure();
    }


//    @RequiresPermissions("sys:myaddress:edit")
    @PostMapping("/updateAddr")
    @ResponseBody
    R updateAddr(GoodsAddressDo goodsAddressDo) {
        try {
            if (addressService.update(goodsAddressDo) > 0) {
                return R.newSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newFailure("用户更新失败");
        }
        return R.newFailure();
    }

//    @RequiresPermissions("sys:myaddress:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Long id) {
        GoodsAddressDo goodsAddressDo = addressService.get(id);
        model.addAttribute("addressmod", goodsAddressDo);
        return prefix+"/addressedit";
    }

//    @RequiresPermissions("sys:myaddress:del")
    @PostMapping("/del")
    @ResponseBody
    R remove(Long id) {
        try {
            if (addressService.remove(id) > 0) {
                return R.newSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.newFailure("删除失败");
        }
        return R.newFailure();
    }


}
