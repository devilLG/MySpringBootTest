package com.zhilai.master.modules.utils.heartbeat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class SiteNoticeHeartBeat extends NoticeHeartBeat{

	/**
	 * 
	 * 此方法描述的是：通知心跳
	 * 
	 * @author: wusijie
	 * @version: 2014-12-26 上午11:07:32
	 */
	public void pushHeart(HeartBeat heartBeat) {
		super.pushHeartBeat(heartBeat);
	}

	/**
	 * 
	 * 此方法描述的是：删除心跳
	 * 
	 * @author: wusijie
	 * @version: 2014-12-26 上午11:07:38
	 */
	public void deleteHeart(HeartBeat heartBeat) {
		super.deleteHeartBeat(heartBeat);
	}
}
