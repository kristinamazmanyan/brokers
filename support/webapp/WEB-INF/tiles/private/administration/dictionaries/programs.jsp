<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page"></tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">
        <ng-view class="col-sm-10 col-sm-offset-1"/>

        <script type="text/ng-template" id="programListView.html">

                <div class="row">

                    <div class="form-group">
                        <label class="col-sm-3 control-label"><spring:message code='page.programs.year'/>:</label>

                        <div class="col-sm-5 selectContainer">
                            <select name="Year" class="form-control" ng-click="loadPage()" ng-model="year">
                                <option value='2016'>2016</option>
                                <option selected value='2015'>2015</option>
                                <option  value='2014'>2014</option>
                                <option  value='2013'>2013</option>
                                <option  value='2012'>2012</option>
                                <option  value='2011'>2011</option>
                            </select>
                        </div>
                    </div>
                    <table class="table col-sm-10 table-hover">
                        <thead>
                        <tr>
                            <th style="width: 60px"><spring:message code='page.programs.code'/></th>
                            <th><spring:message code='page.programs.title'/></th>
                            <td style="width: 60px"><i class="glyphicon glyphicon-align-justify"></i></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="program in programs">
                            <td><a href='#'>{{program.code}}</a></td>
                            <td>
                                <a href='#' iu-switch-lang ng-show="'${pageContext.response.locale}' == 'hy'">{{program.nameHy}}</a>
                                <a href='#' iu-switch-lang ng-show="'${pageContext.response.locale}' == 'en'">{{program.nameEn}}</a>
                                <a href='#' iu-switch-lang ng-show="'${pageContext.response.locale}' == 'ru'">{{program.nameRu}}</a>
                            </td>
                            <td><a href="#/edit/{{program.id}}"><i class="glyphicon glyphicon-edit"></i></a></td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="form-group ">
                        <div class="col-sm-2 col-lg-offset-10">
                            <button type="button" class="btn " ng-click="addProgram()">
                                <spring:message code='commandButton.add'/>
                            </button>
                        </div>
                    </div>


                    <div class=" text-center ">
                        <div class="col-sm-11">
                            <nav <%--ng-show="data.pages > 1"--%>>

                                <pagination total-items="total"
                                            items-per-page="page.size"
                                            ng-model="index "
                                            max-size=5
                                            class="pagination-sm"
                                            boundary-links="true"
                                            rotate="false"
                                            ng-change="loadPage()"
                                        >
                                </pagination>

                            </nav>
                        </div>
                    </div>
                </div>

        </script>

        <script type="text/ng-template" id="programAddEditorView.html">
            <form class="form-horizontal" role="form" ng-submit="addProgram()" data-toggle="validator">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="year"><spring:message code='page.programs.year'/>:</label>

                    <div class="col-sm-4">
                        <input type="text" disabled class="form-control" id="year" ng-model="program.year" >
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="code"><spring:message code='page.programs.code'/>:</label>

                    <div class="col-sm-4">
                        <input name="code"  required id="code" type="text" name="code" class="form-control"
                               placeholder='<spring:message code='page.programs.placeholder.code'/>'
                               ng-model="program.code">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2"><spring:message code='page.programs.title'/>:</label>

                    <div class="col-sm-4" id="nameHy">
                        <div class="input-group">
                          <span class="input-group-addon" ><b iu-lang="hy"></b></span>
                            <input name="nameHy" type="text" class="form-control" placeholder="Մուտքագրել անվանում" required ng-model="program.nameHy">
                        </div>
                    </div>
                    <br/>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2" id="nameEn">
                        <div class="input-group">
                            <span class="input-group-addon" ><b iu-lang="en"></b></span>
                            <input name="nameEn"  type="text" class="form-control" placeholder="Enter Name" required  ng-model="program.nameEn">
                        </div>
                    </div>
                    <br/>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2" id="nameRu">
                        <div class="input-group">
                            <span class="input-group-addon" ><b iu-lang="ru"></b></span>
                            <input name="nameRu" type="text" class="form-control" placeholder="Введите название" required ng-model="program.nameRu">
                        </div>
                    </div>
                    <br/>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn "><spring:message code='commandButton.save'/></button>
                        <button  class="btn "  iu-Button-Back><spring:message code='commandButton.cancel'/> </button>
                    </div>
                </div>
            </form>
        </script>

        <script type="text/ng-template" id="ProgramEditorView.html">

                <form name="editProgramForm" class="form-horizontal" ng-submit="saveChanges()" data-toggle="validator">

                    <div class="form-group">
                        <label class="control-label col-sm-3" for="edit_year"><spring:message code='page.programs.year'/>:</label>
                        <div class="col-sm-4">
                            <input type="text" disabled class="form-control" id="edit_year" value="{{program.year}}">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3" for="edit_code"><spring:message code='page.programs.code'/>:</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" required <%--data-error="<spring:message code='page.programs.required'/>"--%> id="edit_code" name="edit_code" ng-model="program.code" 
                                   placeholder='<spring:message code='page.programs.placeholder.code'/>' >
                        </div>
                        <div class="help-block with-errors"></div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-sm-3"><spring:message code='page.programs.title'/>:</label>
                        <div class="col-sm-4" id="nameHyEd">
                            <div class="input-group">
                                <span class="input-group-addon" ><b iu-lang="hy"></b></span>
                                <input type="text"  name="nameHy" required <%--data-error="<spring:message code='page.programs.required'/>"--%>  class="form-control" placeholder="Enter name" ng-model="program.nameHy">
                            </div>

                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-3" id="nameEnEd">
                            <div class="input-group">
                                <span class="input-group-addon" ><b iu-lang="en"></b></span>
                                <input type="text" name="nameEn"  required <%--data-error="<spring:message code='page.programs.required'/>"--%> class="form-control" placeholder="Enter name" ng-model="program.nameEn">
                            </div>

                        </div>
                       <%-- <div class="help-block with-errors"></div>--%>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-3" id="nameRuEd">
                            <div class="input-group">
                                <span class="input-group-addon" ><b iu-lang="ru"></b></span>
                                <input type="text" name = "nameRu" class="form-control" required <%--data-error="<spring:message code='page.programs.required'/>"--%> placeholder="Enter name" ng-model="program.nameRu">
                            </div>

                        </div>

                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-5">
                            <button type="submit" class="btn "><spring:message code='commandButton.save'/></button>
                            <button type="button" class="btn " iu-Button-Back>
                                <spring:message code='commandButton.cancel'/></button>
                        </div>
                    </div>
                    </form>
                <div class="page-header text-center">
                    <h3> <span class="label label-default ">
                        <spring:message code="page.programs.placeholder.implementing.authorities"/>
                            </span></h3>
                </div>


                    <table class="table  table-hover ">
                        <thead>
                        <tr>
                            <th><spring:message code='page.programs.title'/></th>
                            <th><spring:message code='page.programs.accountNo'/> </th>
                            <td style="width: 60px"><i class="glyphicon glyphicon-align-justify"></i></td>
                        </tr>
                        </thead>
                        <tr ng-repeat="account in accounts">
                            <td><a href=""
                                   e-form="rowform" editable-text="account.authorityNameEn"
                                   e-typeahead="authority as authority.name for authority in getAuthorities($viewValue)"
                                   e-required e-name="authorityInfo"
                                    ng-bind = "showName(account, '${pageContext.response.locale}')">

                            </a></td>
                            <td><a href='' e-form="rowform" e-required e-name="accountNo" editable-text="account.accountNo"  >{{account.accountNo || "empty" }}</a></td>

                            <td style="white-space: nowrap">
                                <!-- form -->
                                <form editable-form name="rowform"  onbeforesave="saveAccount($data, $index)" ng-show="rowform.$visible"
                                      class="form-buttons form-inline" shown="inserted == account">
                                    <button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary">
                                        save
                                    </button>
                                    <button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default">
                                        cancel
                                    </button>
                                </form>
                                <div ng-show="!rowform.$visible">
                                    <span >  <i ng-click="rowform.$show()" class="glyphicon glyphicon-edit"></i>     </span>
                                    <span >  <i ng-click="removeAccount($index)" class="glyphicon glyphicon-remove"></i> </span>
                                </div>
                            </td>
                        </tr>
                        <tfoot>

                        <tr>

                            <td>

                                <input name="accountAuthority"
                                       type="text" ng-model="selected"
                                       typeahead="authority as authority.name for authority in getAuthorities($viewValue)"
                                       typeahead-editable="false"
                                       class="form-control" id="name" placeholder='<spring:message code="page.programs.placeholder.authority"/>'
                                       typeahead-wait-ms = "200"
                                       typeahead-min-length="2"
                                       required  >

                              <span  style="color:red" ng-show=" $error.authority">This field is required</span>
                            </td>

                            <td>  <input  name = "accountNo" type="text" class="form-control" id="num" placeholder='<spring:message code="page.programs.placeholder.accountNo"/>'
                                        ng-model="accountNo" required >
                              <span  style="color:red" ng-show=" $error.account">This field is required</span></td>
                            <td>
                                <button type="button" class="btn pull-left" ng-click="addAccount()" ><spring:message code='commandButton.add'/></button>
                            </td>


                        </tr>
                        </tfoot>
                    </table>

                </div>
        </script>

        <script src="${pageContext.request.contextPath}/static/js/angularjs/jquery.validate.min.js"></script>
        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/generated/components/administration/program_service.js">
        </script>
        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/private/administration/dictonaries/programs.js">
        </script>


    </tiles:putAttribute>
</tiles:insertDefinition>