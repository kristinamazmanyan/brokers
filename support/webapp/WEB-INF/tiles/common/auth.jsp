<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${pageContext['request'].userPrincipal.principal == null}">
    <a id="link-menu-login" onclick="return false;" href="#" class="dropdown-toggle" data-toggle="dropdown"
       role="button" aria-haspopup="true" aria-expanded="false">Login<span class="caret"></span></a>
    <ul class="dropdown-menu">
        <li>
            <div style="width: 200px; margin: 10px">
                <form method="post" action="${pageContext.request.contextPath}/in" id="login-form-menu">
                    <div class="form-group">
                        <label for="username-menu"><spring:message code="page.auth.username"/>:</label>
                        <input id="username-menu" class="form-control" type="text" onfocus="this.select();"
                               name="username" autocomplete="off"
                               placeholder="<spring:message code="page.auth.username" />">
                    </div>
                    <div class="form-group">
                        <label for="password-menu"><spring:message code="page.auth.password"/>:</label>
                        <input id="password-menu" class="form-control" type="password" onfocus="this.select();"
                               name="password" autocomplete="off"
                               placeholder="<spring:message code="page.auth.password" />">
                    </div>
                    <div class="form-group">
                    <button id="btn-login-menu" type="submit" class="btn pull-right"><spring:message
                            code="page.auth.title"/></button>
                        </div>
                </form>
            </div>
        </li>
    </ul>
</c:if>
<script type="text/ng-template" id="autoLoginDialog">
    <div class="modal-header">
        <h3 class="modal-title"><spring:message code="page.auth.title"/></h3>
    </div>
    <div class="modal-body">
        <form ng-hide="isHidden">
            <div ng-hide="isHiddenInf" class="alert alert-info fade in" >
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <spring:message code="modal.dialog.info"/>
            </div>
             <span class="msg-error" ng-show="isError">
                 <div class="alert alert-danger fade in">
                     <a href="#" class="close" data-dismiss="alert">&times;</a>
                     <spring:message  code="modal.dialog.error"/>
                 </div>
                                            </span>
            <div class="form-group">
                <label for="password-menu"><spring:message code="page.auth.password"/>:</label>
                <input id="modal-password-menu" class="form-control" type="password" onfocus="this.select();"
                       ng-model="password" name="password" autocomplete="off"
                       placeholder="<spring:message code="page.auth.password" />">
            </div>

        </form>


        <div ng-show="isHidden">
            <div class="alert alert-success fade in">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <spring:message code="modal.dialog.success"/>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" ng-hide="isHidden" ng-click="login()"><spring:message
                code="page.auth.title"/></button>
        <button class="btn btn-warning" ng-hide="isHidden" ng-click="cancel()"><spring:message
                code="page.auth.logout"/></button>
        <button class="btn btn-warning" ng-show="isHidden" ng-click="ok()"><spring:message
                code="modal.dialog.ok"/></button>
    </div>
</script>


<c:if test="${pageContext['request'].userPrincipal.principal != null}">
    <a href="#" onclick="return false;" class="dropdown-toggle" data-toggle="dropdown" role="button"
       aria-haspopup="true" aria-expanded="false">${pageContext['request'].userPrincipal.principal.username}<span
            class="caret"></span></a>
    <ul class="dropdown-menu">
        <li>
            <a href="${pageContext.request.contextPath}/private/profile"><spring:message
                    code="page.auth.profile"/></a>
            <a href="${pageContext.request.contextPath}/logout"><spring:message code="page.auth.logout"/></a> <%----%>
        </li>
    </ul>
</c:if>