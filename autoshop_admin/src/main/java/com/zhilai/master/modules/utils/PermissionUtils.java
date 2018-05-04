package com.zhilai.master.modules.utils;

import java.lang.reflect.Method;

import com.zhilai.master.modules.sys.entity.User;
import com.zhilai.master.modules.sys.utils.UserUtils;

public class PermissionUtils {
	/**
	 * 控制层验证用户数据访问权限
	 * @param user
	 * @param obj
	 * @param objUrl
	 * @param permissType
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object limitCheck(Object obj){
		try {
			if (null == obj){
				return null;
			}
			User user = UserUtils.getUser();
			Class c = obj.getClass();
			Method methlist[] = c.getDeclaredMethods();
			Method setCorpId = null;
			for (int i = 0; i < methlist.length; i++) {
				Method m = methlist[i];
				//获取到方法对象,此处方法的参数是一个String,method名为setOwner_id
				if (GParameter.permissMethod_corpId.equals(m.getName())){
					setCorpId = c.getMethod(GParameter.permissMethod_corpId, String.class);
				}
			}
			if(setCorpId != null){
				if(user.getCorpId() != null && !user.getCorpId().equals(GParameter.corpId_zhilai)){
					setCorpId.invoke(obj, user.getCorpId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
