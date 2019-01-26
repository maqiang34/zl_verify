package com.smartcold.manage.cold.controller;

import com.smartcold.manage.cold.entity.UserEntity;
import com.smartcold.manage.cold.entity.sys.UserDO;
import com.smartcold.manage.cold.service.sys.UserService;
import com.smartcold.manage.cold.util.MD5Utils;
import com.smartcold.manage.cold.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @ClassName UserController.java
 * @Description: 用户-api
 * @createDate:2018-02-28 16:47:46
 * @version:V1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(HttpServletRequest request, @RequestParam String userName, @RequestParam String password, String ipAddress, Boolean isAuto) {
        try {
            UserDO user = this.userService.getUserByNameAndPwd(new UserDO(userName, password));
            if (user != null) {
                String cookie = MD5Utils.getToken();
//                user.setToken(cookie);
                request.getSession().setAttribute("user", user);
               return R.newData(user);
            }
            return R.newFailure("登录失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return R.newFailure("网络异常！请稍后重试！");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout(HttpServletRequest request, String token) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();//session失效
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return true;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token") || cookie.getName().equals("systoken")) {
                cookie.setMaxAge(0);
            }
        }
        return true;
    }


    /**
     * 备注：将使用spring session 统一托管
     *
     * @param request
     * @param token
     * @return
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public UserEntity findUser(HttpServletRequest request, String token) {
        return (UserEntity) request.getSession().getAttribute("user");
    }
}
