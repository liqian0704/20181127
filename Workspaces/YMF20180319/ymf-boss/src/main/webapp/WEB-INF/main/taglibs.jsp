<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="q" uri="/ajaxquery-tags" %>
<%@ taglib prefix="e" uri="/enumutils" %>
<%@ taglib prefix="dic" uri="/dicutils" %>
<%@ taglib prefix="app" uri="/appfunctionutil" %>
<%@ taglib prefix="checkbox" uri="/checkboxutils" %>
<c:set var="likerPath" value="${pageContext.request.contextPath}" scope="session"/>
<spring:url var="resourceUrl" value="/static"/>
<spring:url var="ctx" value="/"/>

