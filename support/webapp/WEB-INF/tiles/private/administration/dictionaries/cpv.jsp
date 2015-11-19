<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page">ԳՄԱ բառարան</tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">

    <div class="col-sm-10 col-sm-offset-1"  ng-view>
    </div>

    <script type="text/ng-template" id="cpvListView.html">

      <table class="table  table-hover">
        <thead>
        <tr>
          <th style="width: 60px"><spring:message code="page.cpv.code" /></th>
          <th><spring:message code="page.cpv.code.name" />
          <%--  <label class="label label-default" ng-repeat="chk in chkbxs" style="margin-left: 5px;">
              <input type="checkbox" ng-model="chk.val"  ng-disabled = "disable(chk.id)" style="margin-top: 5px;"/> {{chk.label}}
            </label>--%>
          </th>
          <td style="width: 60px"><i class="glyphicon glyphicon-align-justify"></i></td>
        </tr>
        </thead>
        <tr ng-hide="cpvList.length == 0" ng-repeat="cpv in cpvList">
          <td ng_show = last_0(cpv)><a href='#/list/{{cpv.id}}'>{{cpv.code}}</a></td>
          <td ng_hide = last_0(cpv)>{{cpv.code}}</td>
          <td>
            <div class="cpv-name-ru" >{{cpv.nameHy}}</div>
           <%-- <div class="cpv-name-hy" ng-show="checkHy">{{cpvName($index)}}</div>
            <div class="cpv-name-ru" ng-show="checkRu">{{cpvName1($index)}}</div>
            <div class="cpv-name-en" ng-show="checkEn">{{cpvName2($index)}}</div>--%>
          </td>
          <td><a href="#/edit/{{cpv.id}}"><i class="glyphicon glyphicon-edit"></i></a></td>
        </tr>
        <tr ng-show="cpvList.length == 0" >
          <td colspan="3"><div class="well-lg"><spring:message code="page.cpv.notification.data" /> </div></td>
        </tr>
      </table>
    </script>
    <!--CPV LIST EDIT AND ADD TEMPLATE-->
    <script type="text/ng-template" class="cpvEditorView.html" id="cpvAddEditorView.html">
      <form  name="addForm" id="addForm"  data-toggle="validator"  class="form-horizontal">
        <div class="form-group">
          <label for="cpv-add-code" class="col-sm-2 control-label">Code:<span style="color:blue"> * </span></label>
          <div ng-show="!toDo()" class="col-sm-5">
            <input id="cpv-add-code"  class="form-control"  type="text" name="numberOnly" iu-code-format ng-model="cpv.code" />
          </div>
          <div ng-show="toDo()" class="col-sm-5">
            <input id="cpv-edit-code" type="text" class="form-control" ng-model="cpv.code"  readonly/>
          </div>
          <div class="col-sm-5">
            <span ng-show="$error.exist " style="color:red"><spring:message code='page.cpv.code.validation.exist'/></span>
            <span ng-show="$error.digit " style="color:red"><spring:message code='page.cpv.code.validation.digitOnly'/></span>
          </div>
        </div>
        <div class="form-group">
          <label for="cpv-add-nameHy" class="col-sm-2 control-label">Հայերեն:<span style="color:blue"> * </span></label>
          <div class="col-sm-5">
            <input id="cpv-add-nameHy"  required  class="form-control" ng-model="cpv.nameHy"/>

          </div>

        </div>
        <div class="form-group">
          <label for="cpv-add-nameRu" class="col-sm-2 control-label">Русский:<span style="color:blue"> * </span></label>
          <div class="col-sm-5">
            <input id="cpv-add-nameRu" required  class="form-control" ng-model="cpv.nameRu"/>
          </div>


        </div>
        <div class="form-group">
          <label for="cpv-add-nameEn" class="col-sm-2 control-label">English:<span style="color:blue"> * </span></label>
          <div class="col-sm-5">
            <input id="cpv-add-nameEn" required class="form-control" ng-model="cpv.nameEn"/>
          </div>


        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <span class="alert-warning"><spring:message code='page.cpv.code.validation.allFieldsRequired'/></span>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button ng-show="!toDo()" class="btn" ng-click="add()" type="submit"><spring:message code='commandButton.add'/></button>
            <button ng-show="toDo()" id="cpv-edit-btn-save"  class="btn" ng-click="save()"><spring:message code='commandButton.save'/></button>
            <button   id="cpv-edit-btn-cancel" class="btn" iu-Button-Back><spring:message code='commandButton.cancel'/></button
          </div>
        </div>

      </form>
    </script>
    </div>

    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/js/generated/components/administration/cpv_service.js"></script>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/static/js/private/administration/dictonaries/cpv.js"></script>
  </tiles:putAttribute>
</tiles:insertDefinition>