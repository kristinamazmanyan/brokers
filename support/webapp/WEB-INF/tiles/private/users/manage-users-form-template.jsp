<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: artur.tsghunyan
  Date: 6/29/2015
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>

<form  name="addForm" class="form-horizontal" role="form" id="addForm" ng-submit="save()" data-toggle="validator" >
  <div class="col-md-8 col-md-offset-2">

  <%-- User Role --%>
      <div class="form-group">
        <label for="role" class="col-sm-3 control-label"><spring:message code='page.manage-users.role'/>:</label>
        <div class="col-sm-8">
          <select class="form-control" id="role" name="role"  ng-model="user.roleId" >
            <option value="">-- <spring:message code='page.manage-users.chooseRole'/> --</option>
              <option ng-repeat="role in roles" value="{{role.id}}">{{role.nameHy}}</option>
          </select>
        </div>
      </div>

<%-- User First Name --%>
    <%-- User First Name Hy--%>
      <div class="form-group" >
        <label for="name" class="col-sm-3 control-label"><spring:message code='page.manage-users.firstname'/>:</label>
        <div class="col-sm-8" id="name">
          <div class="input-group">
              <span class="input-group-addon" ><b iu-lang="hy"></b></span>
            <input class="form-control" placeholder="Մուտքագրել անունը" required type="text" name="nameHy"  ng-model="user.forenameHy" />
          </div>
        </div>
      </div>

    <%-- User First Name En--%>
      <div class="form-group" >
        <label for="nameEn" class="col-sm-3 control-label"></label>
        <div class="col-sm-8" id="nameEn">
          <div class="input-group">
              <span class="input-group-addon" ><b iu-lang="en"></b></span>
            <input class="form-control" placeholder="Enter name" required type="text" name="nameEn"  ng-model="user.forenameEn" />
          </div>
        </div>
      </div>

    <%-- User First Name Ru--%>
      <div class="form-group" >
        <label for="nameRu" class="col-sm-3 control-label"></label>
        <div class="col-sm-8" id="nameRu">
          <div class="input-group">
              <span class="input-group-addon" ><b iu-lang="ru"></b></span>
            <input class="form-control" placeholder="Введите имя" required type="text" name="nameRu"  ng-model="user.forenameRu" />
          </div>
        </div><br/>
      </div>


<%-- User Last Name --%>
    <%-- User Last Name Hy--%>
    <div class="form-group" >
      <label for="lastnameHy" class="col-sm-3 control-label"><spring:message code='page.manage-users.lastname'/>:</label>
      <div class="col-sm-8" id="lastnameHy">
        <div class="input-group">
            <span class="input-group-addon" ><b iu-lang="hy"></b></span>
          <input class="form-control" placeholder="Մուտքագրել ազգանունը" required type="text" name="lastnameHy"  ng-model="user.surnameHy" />
        </div>
      </div>
    </div>

    <%-- User Last Name En--%>
    <div class="form-group" >
      <label for="lastnameEn" class="col-sm-3 control-label"></label>
      <div class="col-sm-8" id="lastnameEn">
        <div class="input-group">
            <span class="input-group-addon" ><b iu-lang="en"></b></span>
          <input class="form-control" placeholder="Enter last name" required type="text" name="lastnameEn"  ng-model="user.surnameEn" />
        </div>
      </div>
    </div>

    <%-- User Last Name Ru--%>
    <div class="form-group" >
      <label for="lastnameRu" class="col-sm-3 control-label"></label>
      <div class="col-sm-8" id="lastnameRu">
        <div class="input-group">
            <span class="input-group-addon" ><b iu-lang="ru"></b></span>
          <input class="form-control" placeholder="Введите фамилию" required type="text" name="lastnameRu"  ng-model="user.surnameRu" />
        </div>
      </div>
    </div>

    <%--User Login --%>
    <div class="form-group">
      <label for="username" class="col-sm-3 control-label"><spring:message code='page.manage-users.username'/>:</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="username" name="username" required placeholder="<spring:message code='page.manage-users.loginEnter'/>" ng-model="user.username"  />
      </div>
    </div>

    <%-- User Email --%>
    <div class="form-group">
      <label for="email" class="col-sm-3 control-label"><spring:message code='page.manage-users.email'/>:</label>
      <div class="col-sm-8">
        <input type="email" class="form-control" id="email" name="email" required placeholder="<spring:message code='page.manage-users.emailEnter'/>" ng-model="user.email"  />
      </div>
    </div>

    <%-- User Phone --%>
    <div class="form-group">
      <label for="phone" class="col-sm-3 control-label"><spring:message code='page.manage-users.phone'/>:</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="phone" name="phone" placeholder="<spring:message code='page.manage-users.phoneEnter'/>" ng-model="user.phone"  />
      </div>
    </div>

    <%-- User Mobile Phone --%>
    <div class="form-group">
      <label for="phoneMobile" class="col-sm-3 control-label"><spring:message code='page.manage-users.mobilePhone'/>:</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="phoneMobile" name="phoneMobile" placeholder="<spring:message code='page.manage-users.phoneMobileEnter'/>" ng-model="user.phoneMobile"  />
      </div>
    </div>

    <%-- User Status --%>
    <div class="form-group" ng-show="user.id !== undefined">
      <label for="state" class="col-sm-3 control-label"><spring:message code='page.manage-users.status'/>:</label>
      <div class="col-sm-8">
        <select class="form-control" id="state" name="state"  ng-model="user.state" >
          <option value="">-- <spring:message code='page.manage-users.chooseState'/> --</option>
          <option  ng-repeat="id in page.refKeys('UserState') |filter:'!PENDING'" value="{{id}}" >{{id|dictionary:'UserState'}}</option>
        </select>
      </div>
    </div>

      <%-- User Authority --%>
      <div class="form-group" ng-show="getSuperPermission()">
          <label for="authority" class="col-sm-3 control-label"><spring:message code='page.manage-users.authority'/>: </label>

          <div class="col-sm-8">
              <input id="authority" name="accountAuthority"
                     type="text" ng-model="selected"
                     typeahead="authority as authority.name for authority in getAuthorities($viewValue)"
                     typeahead-editable="false"
                     class="form-control"
                     typeahead-wait-ms="200"
                     typeahead-min-length="2"
                      >
          </div>
      </div>

    <%-- User Department --%>
<%--
    TODO user departments must be multiselect
    <div class="form-group"><br/>
      <label for="dept" class="col-sm-2 control-label"><spring:message code='page.manage-users.department'/>:</label>
      <div class="col-sm-8">
        <input type="text" class="form-control" id="dept" name="dept" placeholder="<spring:message code='page.manage-users.deptEnter'/>" ng-model="user.department"  />
      </div><br/>
    </div>--%>

    <%-- User Preffered Language --%>
    <div class="form-group">
      <label for="lang" class="col-sm-3 control-label"><spring:message code='page.manage-users.prefferedLang'/>:</label>
      <div class="col-sm-8">
        <select class="form-control" id="lang" name="lang"  ng-model="user.language" >
          <option value="">-- <spring:message code='page.manage-users.langEnter'/> --</option>
          <option ng-repeat="lang in langs" value="{{lang}}" iu-lang="{{lang}}">{{lang}}</option>
        </select>
      </div>
    </div>


    <div class="form-group"><br/>
        <div class="col-sm-offset-3 col-sm-10">
          <button  class="btn" type="submit"><spring:message code="page.articles.button.save"/></button>
          <a href="#/" class="btn"><spring:message code="page.articles.button.cancel"/></a>
        </div>
      </div>
  </div>
</form>
