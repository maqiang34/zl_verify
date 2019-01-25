package com.smartcold.manage.cold.service.sys;

import com.smartcold.manage.cold.entity.sys.MenuDO;
import com.smartcold.manage.cold.util.TreeModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface MenuService {
	TreeModel<MenuDO> getSysMenuTree(Long id);

	List<TreeModel<MenuDO>> listMenuTree(Long id);

	TreeModel<MenuDO> getTree();

	TreeModel<MenuDO> getTree(Long id);

	List<MenuDO> list(Map<String, Object> params);

	int remove(Long id);

	int save(MenuDO menu);

	int update(MenuDO menu);

	MenuDO get(Long id);

	Set<String> listPerms(Long userId);
}
