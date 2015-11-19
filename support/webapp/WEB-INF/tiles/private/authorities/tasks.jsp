<%--
  Created by IntelliJ IDEA.
  User: artur.tsghunyan
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page"><spring:message code='page.tasks.title'/></tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">


        <%-- Tabs --%>
        <div>
            <ul ng-init="tab = 1" class="nav nav-tabs">
                <li ng-class="{active:tab===1}">
                    <a href="#/tasklist" ng-click="tab = 1"><spring:message code='page.tab.tasks'/></a>
                </li>
                <li ng-class="{active:tab===2}" >
                    <a href="#/calendar" ng-click="tab = 2"><spring:message code='page.tab.calendar'/></a>
                </li>
                <br><br>
            </ul>
        </div>


        <%-- ng-view --%>
        <div  ng-view>        </div>


        <%-- Imports --%>
        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/generated/components/authorities/task_service.js"></script>
        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/private/authority/tasklist/tasks-app.js"></script>


        <%-- Templates --%>
        <script id="template-tasklist" type="text/ng-template">
                <%@include file="tasklist/tasks-list-template.jsp"%>
        </script>

        <script id="template-calendar" type="text/ng-template">
                <%@include file="tasklist/calendar-template.jsp"%>
        </script>


    </tiles:putAttribute>
</tiles:insertDefinition>