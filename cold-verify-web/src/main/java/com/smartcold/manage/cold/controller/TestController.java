package com.smartcold.manage.cold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* @ClassName: TestController
 * @Description:
 * @Date: 2019/1/22：15:43
 * @Author: Maqiang34
 * @Version: V1.0.0
 */
@Controller
public class TestController {

        @RequestMapping(value = "test.htm", method = RequestMethod.GET)
        public String index(String name, Model model) {
            model.addAttribute("message", "hello");
            model.addAttribute("name", name);
            return "/test";
        }

}
