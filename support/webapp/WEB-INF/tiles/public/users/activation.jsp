<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Հաստատեք Ձեր հաշիվը</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="true">


        <div ng-controller="UserActivationController">
            <div class="panel-heading" >
                <h4 class="panel-title">
                    User Activation
                </h4>
            </div>
            <div class="panel panel-default">
                <div class="col-lg-offset-2 col-sm-6">
                    <div class="panel-body">

                        <form class="form-horizontal" id="changePassword" method="get"
                              novalidate
                              name="myForm1" >

                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="password"><spring:message
                                        code="profile.page.new.password.title"/>:</label>

                                <div class="col-sm-8">
                                    <input id="password" class="form-control "
                                           ng-required
                                           ng-model="password" type="password" onfocus="this.select();"
                                           name="password" autocomplete="off"
                                           placeholder=<spring:message code="profile.page.new.password.title"/>>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="password_c"><spring:message
                                        code="profile.page.confirm.password.title"/>:</label>

                                <div class="col-sm-8">
                                    <input id="password_c" class="form-control " type="password" pw-check="password"
                                           ng-required onfocus="this.select();"
                                           name="password_c" autocomplete="off" ng-model="password_c"
                                           placeholder="<spring:message code="profile.page.confirm.password.title" />">

                                    <div class="col-sm-8" class="msg-block" ng-show="myForm1.$error">
                                <span class="msg-error" style="color:red"  ng-show="myForm1.password_c.$error.pwmatch">
                                                 <spring:message code="profile.page.password.dont.match.massage" />
                                            </span>
                                    </div>

                            </div>

                            <div class="form-group">
                                <div class="col-lg-offset-4 col-sm-6">
                                    <button type="button" class="btn "  ng-click="activation()" ng-disabled="!myForm1.$valid">Activate
                                    </button>
                                </div>
                            </div>
                            </div>
                         </form>

                     </div>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>




<script type="application/javascript"
        src="${pageContext.request.contextPath}/static/js/generated/components/users/activation_service.js"></script>

<script type="application/javascript"
        src="${pageContext.request.contextPath}/static/js/public/activation/activation.js"></script>
