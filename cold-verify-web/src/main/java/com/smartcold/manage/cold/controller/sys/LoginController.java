package com.smartcold.manage.cold.controller.sys;

import com.smartcold.manage.cold.controller.BaseController;
import com.smartcold.manage.cold.entity.sys.UserDO;
import com.smartcold.manage.cold.service.sys.MenuService;
import com.smartcold.manage.cold.service.sys.UserService;
import com.smartcold.manage.cold.util.MD5Utils;
import com.smartcold.manage.cold.util.R;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserService userService;



	@GetMapping({ "/", "" })
	String welcome(Model model) {
		System.out.println("登录");
		return "redirect:/login";
	}

//	@GetMapping({ "/index" })
//	String index(Model model) {
//		List<TreeModel<MenuDO>> menus = menuService.listMenuTree(());
//		model.addAttribute("menus", menus);
//		model.addAttribute("username", getUser().getUsername());
//		return "index_v1";
//	}

	@GetMapping("/login")
	String login() {
		System.out.println("开始登录");
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
    R ajaxLogin(UserDO user, HttpServletRequest request) {
		R r = new R();
		try {
			String MD5pwd = MD5Utils.encrypt(user.getUsername(), user.getPassword());
			System.out.println("密码是："+ MD5pwd);
			user.setPassword(MD5pwd);
			UserDO userDO = userService.getUserByNameAndPwd(user);
			request.getSession().setAttribute("userLogin", userDO);
			if (userDO != null) {
				r.put("mod", userDO);
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.newFailure("账号或密码错误");
	}

	@GetMapping("/logout")
	String logout() {
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}


	public static void main(String[] args) {
		System.out.println(" DigestUtils.md5Hex(user.getPassword()):"+ DigestUtils.md5Hex("zxy123"));
	}

}
