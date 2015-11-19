<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">
    <tiles:putAttribute name="title.page"><spring:message code='page.roles.title'/></tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styleRole.css">
        <div class="col-sm-12 " ng-view>
        </div>
       <script type="text/ng-template" id="role">
            <table class="table">
                <thead>
                <tr>
                    <td>
                        <div class="row">
                        <div class="col-xs-6"><spring:message code="page.roles.roles"/></div>
                        <div class="col-xs-offset-11"><a href=""><i ng-click="toggleModal()" class="glyphicon glyphicon-plus"></i></a></div>
                        </div>
                    </td>
                    <td><spring:message code="page.roles.assigned"/></td>
                    <td><spring:message code="page.roles.available"/></td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="text" class="form-control" placeholder="roles" ng-model="searchterm"></td>
                    <td><input type="text" class="form-control" placeholder="assignedPermissions" ng-model="searchterm1"></td>
                    <td><input type="text" class="form-control" placeholder="availablePermissions" ng-model="searchterm2"></td>
                </tr>
                <tr>
                    <td>
                        <ol ng-repeat="role in roles | filter:searchterm | orderBy:'roles'">
                           <div class="row">
                               <div class="col-xs-6">
                                   <span ng-class="{'selected-class-name': $index == selectedIndex}" ng-click="itemClicked($index)">
                                       <a href="" ng-click="getPermission(role.id)">{{role.nameHy}}</a>
                                   </span>
                               </div>
                               <div class="col-xs-offset-10">
                                   <a href=""><i ng-click="toggleModal1(role)" class="glyphicon glyphicon-edit"></i></a>
                                   <a href=""><i ng-click="removeRole(role.id)" class="glyphicon glyphicon-remove"></i></a>
                               </div>
                           </div>
                        </ol>
                    </td>
                    <td colspan="2">
                        <table class="table">
                            <td>
                                <div>
                                    <select multiple id="availabelist" size="20" style="width:275px" ng-change="OnAvailableChange(availablePermissions)" ng-dblclick="OnAvailableClick(this)" ng-model="availablePermissions" ng-options="perm.id as perm.name for perm in Permissions | filter:searchterm1 | orderBy:'name' "></select>
                                </div>
                            </td>
                            <td>
                                <div>
                                    <select multiple id="selectedlist" size="20" style="width:275px" ng-change="OnAssignedChange(assignedPerm)" ng-dblclick="OnAssignedClick(this)" ng-model="assignedPerm" ng-options="perm.id as perm.name for perm in assignedPermissions | filter:searchterm2 | orderBy:'name' "></select>
                                </div>
                            </td>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>

            <modal title="Add Role" visible="showAdd">
                <form name="addForm" id="addForm" ng-submit="roleAdd()" data-toggle="validator" >
                    <div class="form-group" >
                        <label for="name" class="col-sm-2 control-label"><spring:message code="page.roles.name"/>:</label>
                        <div class="col-sm-8" id="name">
                            <div class="input-group">
                                <span class="input-group-addon"><b>Հայ</b></span>
                                <input class="form-control" placeholder="Մուտքագրել անվանում" required type="text" ng-model="role.nameHy" name="nameHy"  />
                            </div>
                        </div>
                        <br/>
                    </div>
                    <div class="form-group" ><br/>
                        <label for="nameEn" class="col-sm-2 control-label"></label>
                        <div class="col-sm-8" id="addNameEn">
                            <div class="input-group">
                                <span class="input-group-addon"><b>Eng</b></span>
                                <input class="form-control" placeholder="Enter title" type="text" required ng-model="role.nameEn" name="nameEn" />
                            </div>
                        </div><br/>
                    </div>
                    <div class="form-group" ><br/>
                        <label for="nameRu" class="col-sm-2 control-label"></label>
                        <div class="col-sm-8" id="addNameRu">
                            <div class="input-group">
                                <span class="input-group-addon"><b>Rus</b></span>
                                <input class="form-control" placeholder="Введите название" required type="text" ng-model="role.nameRu" name="nameRu"  />
                            </div>
                        </div><br/>
                    </div>
                    <div class="form-group"><br/>
                        <div class="col-sm-offset-3 col-sm-10">
                            <button  class="btn" type="submit"> <spring:message code="commandButton.save"/></button>
                            <button  class="btn" ng-click="cancel()"><spring:message code="commandButton.cancel"/></button>
                        </div>
                    </div>
                </form>
            </modal>

            <modal title="Edit Role" visible="showEdit">
                    <form name="EditForm" id="editForm" ng-submit="saveRole()" data-toggle="validator" >
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label"><spring:message code="page.roles.name"/>:</label>
                            <div class="col-sm-8" id="Hay">
                                <div class="input-group">
                                    <span class="input-group-addon"><b>Հայ</b></span>
                                    <input class="form-control" placeholder="Մուտքագրել անվանում" required type="text" ng-model="editRole.nameHy" name="nameHy"  />
                                </div>
                            </div>
                            <br/>
                        </div>
                        <div class="form-group" ><br/>
                            <label for="nameEn" class="col-sm-2 control-label"></label>
                            <div class="col-sm-8" id="nameEn">
                                <div class="input-group">
                                    <span class="input-group-addon"><b>Eng</b></span>
                                    <input class="form-control" placeholder="Enter title" type="text" required  ng-model="editRole.nameEn" name="nameEn" />
                                </div>
                            </div><br/>
                        </div>
                        <div class="form-group" ><br/>
                            <label for="nameRu" class="col-sm-2 control-label"></label>
                            <div class="col-sm-8" id="nameRu">
                                <div class="input-group">
                                    <span class="input-group-addon"><b>Rus</b></span>
                                    <input class="form-control" placeholder="Введите название" type="text" required  ng-model="editRole.nameRu" name="nameRu"  />
                                </div>
                            </div><br/>
                        </div>
                        <div class="form-group"><br/>
                            <div class="col-sm-offset-3 col-sm-10">
                                <button  class="btn" type="submit"><spring:message code="commandButton.save"/></button>
                                <button  class="btn" ng-click="cancel()"><spring:message code="commandButton.cancel"/></button>
                            </div>
                        </div>
                    </form>
                </modal>
        </script>
        <script src="${pageContext.request.contextPath}/static/js/generated/components/administration/role_permission_service.js"></script>.
        <script src="${pageContext.request.contextPath}/static/js/private/administration/dictonaries/roles-app.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>