package com.smartcold.manage.cold.controller.sys;

import com.smartcold.manage.cold.controller.BaseController;
import com.smartcold.manage.cold.entity.sys.MenuDO;
import com.smartcold.manage.cold.service.sys.MenuService;
import com.smartcold.manage.cold.util.R;
import com.smartcold.manage.cold.util.TreeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Bootdo 1992lcg@163.com
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
    MenuService menuService;

//	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

//	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<MenuDO> list(@RequestParam Map<String, Object> params) {
		List<MenuDO> menus = menuService.list(params);
		return menus;
	}

//	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		return prefix + "/add";
	}

//	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		MenuDO mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}

//	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
    R save(MenuDO menu) {
		if (menuService.save(menu) > 0) {
			return R.newSuccess();
		} else {
			return R.newFailure(1, "保存失败");
		}
	}

//	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
    R update(MenuDO menu) {
		if (menuService.update(menu) > 0) {
			return R.newSuccess();
		} else {
			return R.newFailure(1, "更新失败");
		}
	}

//	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
    R remove(Long id) {
		if (menuService.remove(id) > 0) {
			return R.newSuccess();
		} else {
			return R.newFailure(1, "删除失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	TreeModel<MenuDO> tree() {
		TreeModel<MenuDO>  tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	TreeModel<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		TreeModel<MenuDO> tree = menuService.getTree(roleId);
		return tree;
	}
}
