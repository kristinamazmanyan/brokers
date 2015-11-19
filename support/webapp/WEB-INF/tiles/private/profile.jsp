<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Անձնական տվյալներ</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="true">

        <div class="row" ng-controller="ProfileEditorController">
            <div class="col-sm-10 col-sm-offset-1">

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                                    <spring:message code="profile.page.manage.title"/></a>
                            </h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="col-sm-offset-1 col-sm-8">

                                    <form class="form-horizontal" role="form" id="editProfileForm" method="post"
                                          novalidate
                                          name="myForm">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="username-edit"><spring:message
                                                    code="page.login.username"/>:</label>

                                            <div class="col-sm-8">
                                                <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-user"></span></span>
                                                    <input id="username-edit" disabled class="form-control " type="text"
                                                           onfocus="this.select();"
                                                           name="username" autocomplete="off"
                                                           value="{{profile.username}}"
                                                           placeholder="<spring:message code="page.login.username" />">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="role"><spring:message
                                                    code="profile.page.role.title"/>:</label>

                                            <div class="col-sm-8">
                                                <input id="role" disabled class="form-control "
                                                      <%-- {{profile.roleId | dictionary:'Role'}}--%>
                                                       value="{{profile.roleId }}" type="text" onfocus="this.select();"
                                                       name="role" autocomplete="off" aria-describedby="basic-addon2"
                                                       placeholder="<spring:message code="profile.page.role.title" />">
                                            </div>
                                        </div>


                                        <div class="form-group" >

                                            <label class="col-sm-4 control-label" for="forenameHy"><spring:message
                                                    code="profile.page.first.name.title"/>:</label>

                                            <div class="col-sm-8">
                                                <div class="input-group" ng-class="{ 'has-error' : myForm.forenameHy.$invalid && !myForm.forenameHy.$pristine}">
                                                    <span class="input-group-addon" ><b iu-lang="hy"></b></span>
                                                    <input id="forenameHy" class="form-control " ng-model="profile.forenameHy"
                                                           value="{{profile.forenameHy}}" required type="text"
                                                           onfocus="this.select();"
                                                           name="forenameHy" autocomplete="off" aria-describedby="basic-addon1"
                                                           placeholder="<spring:message code="profile.page.first.name.title" />">

                                                </div>

                                                <div class="input-group" ng-class="{ 'has-error' : myForm.forenameEn.$invalid && !myForm.forenameEn.$pristine }">
                                                    <span class="input-group-addon" ><b iu-lang="en"></b></span>
                                                    <input id="forenameEn" class="form-control " ng-model="profile.forenameEn"
                                                           value="{{profile.forenameEn}}" required type="text"
                                                           onfocus="this.select();"
                                                           name="forenameEn" autocomplete="off" aria-describedby="basic-addon6"
                                                           placeholder="<spring:message code="profile.page.first.name.title" />">

                                                </div>
                                                <div class="input-group" ng-class="{ 'has-error' :  myForm.forenameRu.$invalid && !myForm.forenameRu.$pristine}">
                                                    <span class="input-group-addon" ><b iu-lang="ru"></b></span>
                                                    <input id="forenameRu" class="form-control " ng-model="profile.forenameRu"
                                                           value="{{profile.forenameRu}}" required type="text"
                                                           onfocus="this.select();"
                                                           name="forenameRu" autocomplete="off" aria-describedby="basic-addon5"
                                                           placeholder="<spring:message code="profile.page.first.name.title" />">

                                                </div>

                                                <p ng-show="myForm.forenameHy.$invalid && !myForm.forenameHy.$pristine
                                        || myForm.forenameEn.$invalid && !myForm.forenameEn.$pristine || myForm.forenameRu.$invalid && !myForm.forenameRu.$pristine"
                                                   class="help-block"><spring:message
                                                        code="profile.page.first.name.required.message"/></p>

                                            </div>
                                        </div>




                                        <div class="form-group"
                                                >
                                            <label class="col-sm-4 control-label" for="surnameHy"><spring:message
                                                    code="profile.page.last.name.title"/>:</label>

                                            <div class="col-sm-8">
                                                <div class="input-group"  ng-class="{ 'has-error' : myForm.surnameHy.$invalid && !myForm.surnameHy.$pristine }">
                                                    <span class="input-group-addon" ><b iu-lang="hy"></b></span>
                                                    <input id="surnameHy" class="form-control " ng-model="profile.surnameHy"
                                                           value="{{profile.surnameHy}}" required type="text"
                                                           onfocus="this.select();"
                                                           name="surnameHy" autocomplete="off" aria-describedby="basic-addon2"
                                                           placeholder="<spring:message code="profile.page.last.name.title" />">

                                                </div>

                                                <div class="input-group"  ng-class="{ 'has-error' : myForm.surnameEn.$invalid && !myForm.surNameEn.$pristine }">
                                                    <span class="input-group-addon" ><b iu-lang="en"></b></span>
                                                    <input id="surnameEn" class="form-control " ng-model="profile.surnameEn"
                                                           value="{{profile.surnameEn}}" required type="text"
                                                           onfocus="this.select();"
                                                           name="surnameEn" autocomplete="off" aria-describedby="basic-addon7"
                                                           placeholder="<spring:message code="profile.page.last.name.title" />">

                                                </div>

                                                <div class="input-group"  ng-class="{ 'has-error' : myForm.surnameRu.$invalid && !myForm.surnameRu.$pristine }">
                                                    <span class="input-group-addon" ><b iu-lang="ru"></b></span>
                                                    <input id="surnameRu" class="form-control " ng-model="profile.surnameRu"
                                                           value="{{profile.surnameRu}}" required type="text"
                                                           onfocus="this.select();"
                                                           name="surnameRu" autocomplete="off" aria-describedby="basic-addon8"
                                                           placeholder="<spring:message code="profile.page.last.name.title" />">

                                                </div>

                                                <p ng-show="myForm.surnameHy.$invalid && !myForm.surnameHy.$pristine
                                        || myForm.surnameEn.$invalid && !myForm.surnameEn.$pristine || myForm.surnameRu.$invalid && !myForm.surnameRu.$pristine"
                                                   class="help-block"><spring:message
                                                        code="profile.page.first.name.required.message"/></p>
                                            </div>
                                        </div>


                                        <div class="form-group"
                                             ng-class="{ 'has-error' : myForm.email.$invalid && !myForm.email.$pristine }">
                                            <label class="col-sm-4 control-label" for="email"><spring:message
                                                    code="profile.page.email.title"/>:</label>

                                            <div class="col-sm-8">
                                                <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-envelope"></span></span>
                                                    <input id="email" class="form-control " ng-model="profile.email"
                                                           value="{{profile.email}}" required type="email"
                                                           onfocus="this.select();"
                                                           name="email" autocomplete="off" aria-describedby="basic-addon3"
                                                           placeholder="<spring:message code="profile.page.email.title" />">

                                                </div>
                                                <p ng-show="myForm.email.$invalid && !myForm.email.$pristine"
                                                   class="help-block"><spring:message
                                                        code="profile.page.email.valid.massage"/></p>
                                            </div>
                                        </div>

                                        <div class="form-group" >
                                            <label class="col-sm-4 control-label" for="phone"><spring:message
                                                    code="profile.page.phone.title"/>:</label>

                                            <div class="col-sm-8">
                                                <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-phone-alt"></span></span>
                                                    <input id="phone" class="form-control input-medium bfh-phone" ng-model="profile.phone"
                                                           value="{{profile.phone}}"  type="tel" data-format="(ddd) ddd-ddd"
                                                           onfocus="this.select();"
                                                           name="phone" autocomplete="off"
                                                           placeholder="<spring:message code="profile.page.phone.title" />">

                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group" >
                                            <label class="col-sm-4 control-label" for="phoneMobile"><spring:message
                                                    code="profile.page.mobile.title"/>:</label>

                                            <div class="col-sm-8">
                                                <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-earphone"></span></span>
                                                    <input id="phoneMobile" class="form-control input-medium bfh-phone" ng-model="profile.phoneMobile"
                                                           value="{{profile.phoneMobile}}"   type="tel" data-format="(ddd) dd-dd-dd"
                                                           onfocus="this.select();"
                                                           name="phoneMobile" autocomplete="off"
                                                           placeholder="<spring:message code="profile.page.mobile.title" />">

                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-4 control-label" for="prefered-language"><spring:message
                                                    code="profile.page.prefered.lang.title"/>:</label>

                                            <div class="col-sm-8">
                                                <select id="prefered-language" ng-model="profile.language"
                                                        class="form-control" check-language>
                                                    <option ng-repeat="(language, name) in page.refs('Locale')"
                                                            value="{{language}}"
                                                            ng-selected="equal(language)">{{name}}
                                                    </option>
                                                </select>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <div class="col-lg-offset-4 col-sm-6">

                                                <button
                                                        type="button" class="btn " ng-click="cancel()">
                                                    <spring:message code="page.manage-users.button.cancel"/>
                                                </button>
                                                <button
                                                        type="button" ng-disabled="myForm.$invalid" class="btn "
                                                        ng-click="update()">
                                                    <spring:message code="page.manage-users.button.save"/>
                                                </button>
                                            </div>
                                        </div>


                                    </form>

                                </div>

                            </div>
                            <div class="alert alert-info" role="alert">

                                <spring:message
                                        code="profile.page.info.msg"/>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2"><spring:message
                                        code="profile.page.change.password.title"/></a>
                            </h4>
                        </div>
                        <div id="collapse2" class="panel-collapse collapse">
                            <div class="panel-body">
                                <form class="form-horizontal" id="changePassword" method="post"
                                      novalidate
                                      name="myForm1">

                                    <div class="col-sm-offset-1 col-sm-8">
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label" for="currentPassword"><spring:message
                                                code="profile.page.current.password.title"/>:</label>

                                        <div class="col-sm-8">
                                            <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-lock"></span></span>
                                                <input id="currentPassword" class="form-control "
                                                       ng-required
                                                       ng-model="currentPassword" type="password" onfocus="this.select();"
                                                       name="currentPassword" autocomplete="off"
                                                       placeholder="<spring:message code="profile.page.current.password.title" />">
                                            </div>
                                  <%--  <span class="msg-error" ng-show="isError">
                                                {{error}}
                                            </span>--%>


                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label class="col-sm-4 control-label" for="password"><spring:message
                                                code="profile.page.new.password.title"/>:</label>

                                        <div class="col-sm-8">
                                            <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-lock"></span></span>
                                                <input id="password" class="form-control "
                                                       ng-required
                                                       ng-model="password" type="password" onfocus="this.select();"
                                                       name="password" autocomplete="off"
                                                       placeholder="<spring:message code="profile.page.new.password.title" />">

                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-4 control-label" for="password_c"><spring:message
                                                code="profile.page.confirm.password.title"/>:</label>

                                        <div class="col-sm-8">
                                            <div class="input-group">
                                            <span class="input-group-addon"><span
                                                    class="glyphicon glyphicon-lock"></span></span>
                                                <input id="password_c" class="form-control " type="password" pw-check="password"
                                                       ng-required onfocus="this.select();"
                                                       name="password_c" autocomplete="off" ng-model="profile.password_c"
                                                       placeholder="<spring:message code="profile.page.confirm.password.title" />">
                                            </div>
                                            <div class="msg-block" ng-show="myForm1.$error">
                                <span style="color:red" class="msg-error" ng-show="myForm1.password_c.$error.pwmatch">
                                                <spring:message code="profile.page.password.dont.match.massage"/>
                                            </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-4 col-sm-6">
                                            <button type="button" class="btn " ng-click="cancel()"  >
                                                <spring:message code="page.manage-users.button.cancel"/>
                                            </button>
                                            <button type="button" class="btn" ng-disabled="!myForm1.$valid"
                                                    ng-click="changePassword()">
                                                <spring:message code="page.manage-users.button.save"/>
                                            </button>
                                        </div>
                                    </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/generated/components/users/profile_service.js"></script>
        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/private/profile/profile.js"></script>

    </tiles:putAttribute>
</tiles:insertDefinition>

