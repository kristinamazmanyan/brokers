<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<tiles:definition name="default"  template="/WEB-INF/tiles/common/layout/layout.jsp">
    <tiles:putAttribute name="auth.popup" value="/WEB-INF/tiles/common/auth.jsp"/>
    <tiles:putAttribute name="title.page" value="page.home.title"/>
    <tiles:putAttribute name="header" value="/WEB-INF/tiles/common/header.jsp"/>
    <tiles:putAttribute name="footer" value="/WEB-INF/tiles/common/footer.jsp"/>
</tiles:definition>