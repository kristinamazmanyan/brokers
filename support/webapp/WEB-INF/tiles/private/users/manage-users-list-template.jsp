<%--
  Created by IntelliJ IDEA.
  User: artur.tsghunyan
  Date: 6/29/2015
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<form ng-init="list()">
  <div class="row" >
  <table class="table table-hover">
    <thead>
    <tr>
      <th><spring:message code='page.manage-users.name'/></th>
      <th><spring:message code='page.manage-users.role'/></th>
      <th><spring:message code='page.manage-users.status'/></th>
      <th><spring:message code='page.manage-users.lastLoggedIn'/></th>
      <td style="width: 60px"><i class="glyphicon glyphicon-align-justify"></i></td>
    </tr>
    </thead>
    <tr ng-hide="userList.length === 0" ng-repeat="user in userList">
      <td ><a href="#/view/{{user.id}}">
        <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{user.forenameHy}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'en'">{{user.forenameEn}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{user.forenameRu}}</span>
      </a></td>

      <td>
        <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{user.roleNameHy}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'en'">{{user.roleNameEn}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{user.roleNameRu}}</span>
      </td>

      <td> {{user.state | dictionary:'UserState' }} </td>

      <td> {{user.lastLoggedIn | date : 'hh:mm dd/MM/yyyy  '}} </td>


      <td><a href="#/edit/{{user.id}}"><i class="glyphicon glyphicon-edit"></i></a></td>
    </tr>

    <tr ng-show="cpvList.length == 0" >
      <td colspan="3"><div class="well-lg"><spring:message code='page.manage-users.noDataToShow'/></div></td>
    </tr>
  </table>

  <div class="col-sm-10 center-block">
    <a href="#/add"><button class="btn "><spring:message code="page.articles.button.add"/></button></a>
  </div>

  <div class="col-md-10 col-md-offset-5">
    <pagination total-items= "totalCount"
                items-per-page="itemsPerPage"
                ng-model="currentPage"
                class="pagination-sm"
                boundary-links="true"
                rotate="false"
                ng-change = "list()"
                first-text="<<"
                last-text=">>"
                previous-text="<"
                next-text=">"
            >
    </pagination>
  </div>

</div>
</form>
