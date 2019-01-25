package com.smartcold.manage.cold.controller.sys;

import com.smartcold.manage.cold.controller.BaseController;
import com.smartcold.manage.cold.entity.sys.DeptDO;
import com.smartcold.manage.cold.entity.sys.RoleDO;
import com.smartcold.manage.cold.entity.sys.UserDO;
import com.smartcold.manage.cold.entity.sys.UserVO;
import com.smartcold.manage.cold.service.sys.RoleService;
import com.smartcold.manage.cold.service.sys.UserService;
import com.smartcold.manage.cold.util.PageUtils;
import com.smartcold.manage.cold.util.Query;
import com.smartcold.manage.cold.util.R;
import com.smartcold.manage.cold.util.TreeModel;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/user")
@Controller
public class SysUserController extends BaseController {
	private String prefix="system/user"  ;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
    UserService userService;
	@Autowired
    RoleService roleService;

//	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		logger.info("===========查询队列表数据");
		// 查询列表数据
		Query query = new Query(params);
        logger.info("Query:"+query.toString());
        if (query.get("deptId") == null || "".equals(query.get("deptId"))) {
			query.put("deptId", 0);
		}else{
        	String object = (String)query.get("deptId");
        	query.put("deptId", Long.valueOf(object));
		}
		List<UserDO> sysUserList = userService.selectlistByDeptId(query);
		int total = 0;
		if (sysUserList.isEmpty()) {
			sysUserList = userService.list(query);
			total = userService.count(query);
		}else{
			total = userService.countListByDeptId(query);
		}
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

//	@RequiresPermissions("sys:user:add")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

//	@RequiresPermissions("sys:user:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

//	@RequiresPermissions("sys:user:add")
	@PostMapping("/save")
	@ResponseBody
    R save(UserDO user) {
//		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return R.newSuccess();
		}
		return R.newFailure();
	}

//	@RequiresPermissions("sys:user:edit")
	@PostMapping("/update")
	@ResponseBody
    R update(UserDO user) {
		if (userService.update(user) > 0) {
			return R.newSuccess();
		}
		return R.newFailure();
	}


//	@RequiresPermissions("sys:user:edit")
	@PostMapping("/updatePeronal")
	@ResponseBody
    R updatePeronal(UserDO user) {
		if (userService.updatePersonal(user) > 0) {
			return R.newSuccess();
		}
		return R.newFailure();
	}


//	@RequiresPermissions("sys:user:remove")
	@PostMapping("/remove")
	@ResponseBody
    R remove(Long id) {
		if (userService.remove(id) > 0) {
			return R.newSuccess();
		}
		return R.newFailure();
	}

//	@RequiresPermissions("sys:user:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.newSuccess();
		}
		return R.newFailure();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

//	@RequiresPermissions("sys:user:resetPwd")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@PostMapping("/resetPwd")
	@ResponseBody
    R resetPwd(UserVO userVO, HttpServletRequest request) {
		try{
			UserDO userDO = (UserDO) request.getSession().getAttribute("userLogin");
			userService.resetPwd(userVO, userDO);
			// 登出系统
//			SecurityUtils.getSubject().logout();
			return R.newSuccess("密码更新成功，请重新登录确保功能正常使用！");
		}catch (Exception e){
			return R.newFailure(1,e.getMessage());
		}

	}
//	@RequiresPermissions("sys:user:resetPwd")
	@PostMapping("/adminResetPwd")
	@ResponseBody
    R adminResetPwd(UserVO userVO) {
		try{
			userService.adminResetPwd(userVO);
			return R.newSuccess();
		}catch (Exception e){
			return R.newFailure(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public TreeModel<DeptDO> tree() {
		logger.info("==========获取用户树");
		TreeModel<DeptDO> tree = new TreeModel<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

//	/**
//	 * 个人中心
//	 * @param model
//	 * @return
//	 */
//	@GetMapping("/personal")
//	String personal(Model model) {
//		UserDO userDO  = userService.get(getUserId());
//		model.addAttribute("user",userDO);
//		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
//		model.addAttribute("sexList",dictService.getSexList());
//		return prefix + "/personal";
//	}


	@RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public UserDO findUser(HttpServletRequest request, String token) {
		System.out.println("查询用户是否存在");
        return (UserDO) request.getSession().getAttribute("userLogin");
    }
}
