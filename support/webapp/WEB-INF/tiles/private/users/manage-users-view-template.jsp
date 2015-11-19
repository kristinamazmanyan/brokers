<%--
  Created by IntelliJ IDEA.
  User: artur.tsghunyan
  Date: 7/3/2015
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-6 col-md-offset-3">

  <table class=" table table-row-cell table-responsive" >
    <thead></thead>

    <tr>
      <td style="width: 200px"><spring:message code='page.manage-users.firstname'/></td>
      <td >
        <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{user.forenameHy}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'en'">{{user.forenameEn}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{user.forenameRu}}</span>
      </td>
    </tr>
    <tr>
      <td ><spring:message code='page.manage-users.lastname'/></td>
      <td >
        <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{user.surnameHy}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'en'">{{user.surnameEn}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{user.surnameRu}}</span>
      </td>
    </tr>

    <tr>
      <td><spring:message code='page.manage-users.role'/></td>
      <td>
        <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{user.roleNameHy}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'en'">{{user.roleNameEn}}</span>
        <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{user.roleNameRu}}</span>
      </td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.status'/></td>
      <td>{{user.state | dictionary:'UserState'}}</td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.username'/></td>
      <td>{{user.username}}</td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.email'/></td>
      <td>{{user.email}}</td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.phone'/></td>
      <td>{{user.phone}}</td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.mobilePhone'/></td>
      <td>{{user.phoneMobile}}</td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.department'/></td>
      <td>{{user.department}}</td>
    </tr>
    <tr>
      <td><spring:message code='page.manage-users.prefferedLang'/></td>
      <td><b iu-lang="{{user.language}}"></b></td>
    </tr>

  </table>

  <div class="col-md-6 col-md-offset-4">
    <a href="#/edit/{{user.id}}"><button class="btn btn-default "><spring:message code='page.manage-users.button.edit'/></button></a>
    <a href="#/"><button class="btn btn-default "><spring:message code='page.manage-users.button.back'/></button></a>
  </div>



</div>
