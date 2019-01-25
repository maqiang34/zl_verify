package com.smartcold.manage.cold.service.sys;


import com.smartcold.manage.cold.entity.sys.DeptDO;
import com.smartcold.manage.cold.util.TreeModel;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	TreeModel<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
