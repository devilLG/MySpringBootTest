<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ attribute name="category" type="com.zhilai.master.modules.cms.entity.Category" required="true" description="栏目对象"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="false" description="页面大小"%>
<c:forEach items="${fnc:getArticleList(category.site.logid, category.logid, not empty pageSize?pageSize:8, 'posid:2, orderBy: \"hits desc\"')}" var="article">
	<li><a href="${ctx}/view-${article.category.logid}-${article.id}${urlSuffix}" style="color:${article.color}" title="${article.title}">${fns:abbr(article.title,16)}</a></li>
</c:forEach>