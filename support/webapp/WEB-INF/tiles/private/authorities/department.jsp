<%--
  Created by IntelliJ IDEA.
  User: Student2
  Date: 8/24/2015
  Time: 1:16 PM
  To change this template use File | Settings | File Templates.

--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">
    <tiles:putAttribute name="title.page"><spring:message code="page.department.title"/></tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false" >
      <%--<ng-view/>--%>
      <div class="row">
        <%--    <script type="text/ng-template" id="department">--%>
    <table class="table">
        <thead>
        <tr>
            <td>
                <div class="row">
                    <div class="col-xs-6"><spring:message code="page.department.department"/></div>
                    <div class="col-xs-offset-11"><a href=""><i ng-click="toggleModal()" class="glyphicon glyphicon-plus"></i></a></div>
                </div>
            </td>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="de in department">
            <td>{{de.nameHy}}</td>
        </tr>
        </tbody>
    </table>

    <modal title="Add department" visible="showAdd">
      <form name="addForm" id="addForm" ng-submit="addDepartment()" novalidate >
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label"><spring:message code="page.department.name"/>:</label>
                <div class="col-sm-8" id="name">
                    <div class="input-group">
                            <%--
                                                    <span class="input-group-addon"><b>Հայ</b></span>--%>
    <input class="form-control" placeholder="Մուտքագրել անվանում" type="text" name="name"  />
                    </div>
                </div>
                <br/>
            </div>
            <div class="form-group"><br/>
                <div class="col-sm-offset-3 col-sm-10">
                    <button  class="btn" type="submit"> <spring:message code="commandButton.save"/></button>
                    <button  class="btn" ng-click="cancel()"><spring:message code="commandButton.cancel"/></button>
                </div>
            </div>
        </form>
  </modal>
        <%--
            <modal title="Edit Role" visible="showEdit">
                <form name="EditForm" id="editForm" ng-submit="saveRole()" novalidate >
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><spring:message code="page.roles.name"/>:</label>
                        <div class="col-sm-8" id="Hay">
                            <div class="input-group">
                                <span class="input-group-addon"><b>Հայ</b></span>
                                <input class="form-control" placeholder="Մուտքագրել անվանում" type="text" ng-model="editRole.nameHy" name="nameHy"  />
                            </div>
                        </div>
                        <br/>
                    </div>
                    <div class="form-group" ><br/>
                        <label for="nameEn" class="col-sm-2 control-label"></label>
                        <div class="col-sm-8" id="nameEn">
                            <div class="input-group">
                                <span class="input-group-addon"><b>Eng</b></span>
                                <input class="form-control" placeholder="Enter title" type="text"  ng-model="editRole.nameEn" name="nameEn" />
                            </div>
                        </div><br/>
                    </div>
                    <div class="form-group" ><br/>
                        <label for="nameRu" class="col-sm-2 control-label"></label>
                        <div class="col-sm-8" id="nameRu">
                            <div class="input-group">
                                <span class="input-group-addon"><b>Rus</b></span>
                                <input class="form-control" placeholder="Введите название" type="text"  ng-model="editRole.nameRu" name="nameRu"  />
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
            </modal>--%>
</div>
        <script src ="${pageContext.request.contextPath}/static/js/generated/components/administration/department_user_service.js"></script>
        <script src ="${pageContext.request.contextPath}/static/js/private/authority/department-app.js"/>
      <%--   </script>--%>
</tiles:putAttribute>
  </tiles:insertDefinition>