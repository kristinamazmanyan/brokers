<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Գնումների պլան</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">

        <div class="row">
            <div class="col-sm-12">
                <jsp:include page="/WEB-INF/tiles/common/pplan/pplan-view.jsp">
                    <jsp:param name="authorityId" value="all"/>
                    <jsp:param name="mode" value="view"/>
                </jsp:include>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>