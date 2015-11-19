<%@ include    file="/WEB-INF/tiles/common/layout/definitions.jsp"%>
<tiles:insertDefinition name="default" flush="true">
    <tiles:putAttribute name="title.page"><spring:message code="page.public.welcome.title"/></tiles:putAttribute>

    <tiles:putAttribute name="body">
        <div class="row">
            <div class="col-md-5 col-md-offset-4">
                <c:if test="${param['reason']=='logged.out'}">
                    <p><spring:message code="page.login.notification.successfylly_logged_out"/></p>
                </c:if>
                <c:if test="${param['reason']=='login.failed'}">
                    <p><spring:message code="page.login.notification.invalid_username_password"/></p>
                </c:if>
                <form  class="form-horizontal" id="LoginForm" method="post" action="${pageContext.request.contextPath}/in" onsubmit="return validateLoginForm(this)">
                    <div class="form-group">
                        <p>
                            <label for="Username" class="col-xs-4 control-label"><spring:message code="page.login.username" />:</label>
                        <div class="col-xs-5">
                            <input id="Username"  class="form-control" type="text" onfocus="this.select();" name="username" autocomplete="off">
                        </div>
                        </p>
                    </div>

                    <div class="form-group">
                        <p>
                            <label for="Password"  class="col-xs-4 control-label"><spring:message code="page.login.password" />:</label>
                        <div class="col-xs-5">
                            <input type="password" name="password" size="10" value="" class="form-control" onfocus="this.select();" id="Password">
                        </div>
                        </p>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-10">
                            <p>
                                <button id="btn-login-menu" id="LogIn" type="submit" class="btn">
                                    <spring:message code="page.login.title"/>
                                </button>
                            </p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>