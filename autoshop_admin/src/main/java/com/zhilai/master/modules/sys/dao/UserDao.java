package com.zhilai.master.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.sys.entity.ChatUser;
import com.zhilai.master.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author zhilai
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	/**
	 * 根据站点ID查询用户信息
	 * @param user
	 * @return
	 * */
	public User findOneById(String loginName);
	/**
	 * 根据Login_id与Password查询用户信息
	 * @param user
	 * @return
	 * */
	public User findByUserInfo(User user);

	/**
	 * 
	 * @Title findByRole
	 * @Description 根据角色英文名，公司ID查询第一个该公司该角色的用户
	 * @Author GuoXuegan
	 * @data 2018年4月11日下午3:32:04
	 * @param ename
	 * @param corp_id
	 * @return
	 *
	 */
	public User findByRole(@Param(value="ename")String ename, @Param(value="corp_id")String corp_id);

	/**
	 * 
	 * @Title findByInventory
	 * @Description 根据站点编号查询某站点的商品维护人员
	 * @Author GuoXuegan
	 * @data 2018年4月11日下午5:00:19
	 * @param corpId
	 * @return
	 *
	 */
	public User findByInventory(String site_id);
}
