package com.smartcold.manage.cold.service.sys;

import com.smartcold.manage.cold.entity.sys.DeptDO;
import com.smartcold.manage.cold.entity.sys.UserDO;
import com.smartcold.manage.cold.entity.sys.UserVO;
import com.smartcold.manage.cold.util.TreeModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface UserService {
	UserDO get(Long id);

	List<UserDO> list(Map<String, Object> map);

	List<UserDO> selectlistByDeptId(Map<String, Object> map);

	UserDO getUserByNameAndPwd(Map<String, Object> map);

	int count(Map<String, Object> map);

	int countListByDeptId(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

//	int updatestatus(UserDO userDO);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	TreeModel<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

}
