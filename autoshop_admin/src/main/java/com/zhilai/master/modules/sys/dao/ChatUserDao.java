package com.zhilai.master.modules.sys.dao;

import com.zhilai.master.common.persistence.CrudDao;
import com.zhilai.master.common.persistence.annotation.MyBatisDao;
import com.zhilai.master.modules.sys.entity.ChatUser;

/**
 * 用户DAO接口
 * @author zhilai
 * @version 2014-05-16
 */
@MyBatisDao
public interface ChatUserDao extends CrudDao<ChatUser> {
	public ChatUser findChatUser(ChatUser user);
	public int insert(ChatUser user);
	public ChatUser loginChatUser(ChatUser user);
	public int updatePwd(ChatUser user);
}
