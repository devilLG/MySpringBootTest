package com.zhilai.master.modules.notice.service;	import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import com.zhilai.master.common.service.CrudService;import com.zhilai.master.modules.notice.dao.NoticeOverDao;import com.zhilai.master.modules.notice.entity.NoticeOver;	@Service@Transactional(readOnly=true)public class NoticeOverService extends CrudService<NoticeOverDao, NoticeOver> {	}