<%--
  Created by IntelliJ IDEA.
  User: artur.tsghunyan
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page"><spring:message code='page.manage-users.title'/></tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">

    <div  ng-view class="col-sm-10 col-sm-offset-1">
    </div>


    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/js/generated/components/users/authority_user_service.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/js/private/authority/user/users-app.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/js/angularjs/jquery.validate.min.js"></script>



    <script id="template-list-users" type="text/ng-template">
      <%@include file="users/manage-users-list-template.jsp"%>
    </script>

    <script id="template-form-users" type="text/ng-template">
      <%@include file="users/manage-users-form-template.jsp"%>
    </script>

    <script id="template-view-users" type="text/ng-template">
      <%@include file="users/manage-users-view-template.jsp"%>
    </script>

  </tiles:putAttribute>
</tiles:insertDefinition>