package com.smartcold.manage.cold.dao.sys;

import com.smartcold.manage.cold.entity.sys.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(UserDO map);

	UserDO getUserByNameAndPwd(UserDO map);

	List<UserDO> selectlistByDeptId(UserDO map);

	int count(Map<String, Object> map);

	int countListByDeptId(UserDO map);

	int save(UserDO user);
	
	int update(UserDO user);

	int updatestatus(UserDO userDO);

	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
