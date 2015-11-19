<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page"><spring:message code="page.faq.title"/></tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">
        <div>
            <h5><spring:message code="http.error.${code}.message"/></h5>
        </div>
        <h6><spring:message htmlEscape="false" code="http.error.${code}.description"
                            arguments="${pageContext.request.contextPath}"/></h6>
    </tiles:putAttribute>
</tiles:insertDefinition>