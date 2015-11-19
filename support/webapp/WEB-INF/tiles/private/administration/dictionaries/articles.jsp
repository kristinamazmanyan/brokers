<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page"><spring:message code='page.articles-page.title'/></tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">

    <div class="col-sm-10 col-sm-offset-1" ng-view>
    </div>

    <script type="text/ng-template" id="articleList" >
      <form ng-init="list()">
      <div class="form-group" >
        <label for="year" class="col-sm-3 control-label"><spring:message code="page.articles.year"/>:</label>
        <div class="col-sm-5 selectContainer">
          <select id="year" name="year" class="form-control">
            <option >2016</option>
            <option >2015</option>
          </select><br/>
        </div>
      </div>
      <div>
        <table class="table col-sm-10 table-hover" >
          <thead>
          <tr>
            <th style="width: 20%"><spring:message code="page.articles.code"/></th>
            <th><spring:message code="page.articles.title"/></th>
            <td style="width: 60px"><i class="glyphicon glyphicon-align-justify"></i></td>
          </tr>
          </thead>

          <tr ng-repeat="a in articles">
            <td >{{a.code}}</td>
            <td >
              <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{a.nameHy}}</span>
              <span  ng-show="'${pageContext.response.locale}' == 'en'">{{a.nameEn}}</span>
              <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{a.nameRu}}</span>
            </td>
            <td><a href="#/edit/{{a.id}}"><i class="glyphicon glyphicon-edit"></i></a></td>
          </tr>
        </table>
      </div>

      <div class="col-sm-10 center-block">
        <a href="#/add/"><button class="btn "><spring:message code="page.articles.button.add"/></button></a>
      </div>

      <div class="col-md-10 col-md-offset-3">
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

      </form>
    </script>

    <script type="text/ng-template" id="addArticle" >

      <form name="addForm" id="addForm" class="form-horizontal" role="form" ng-submit="add()" data-toggle="validator" >
        <div class="form-group">
          <label for="code" class="col-sm-2 control-label"><spring:message code="page.articles.code"/>:</label>
          <div class="col-sm-8">
            <input type="text" class="form-control" id="code" name="code" required placeholder="<spring:message code='page.articles.enterCode'/>" ng-model="article.code"  />
          </div>
        </div>

        <div class="form-group" >
          <label for="name" class="col-sm-2 control-label"><spring:message code="page.articles.title"/>:</label>

          <div class="col-sm-8" id="name">
            <div class="input-group">
              <span class="input-group-addon" ><b iu-lang="hy"></b></span>
              <input class="form-control" required placeholder="Մուտքագրել անվանում" type="text" name="nameHy"  ng-model="article.nameHy" />
            </div>
          </div>
        </div>

        <div class="form-group" >
          <label for="nameEn" class="col-sm-2 control-label"></label>

          <div class="col-sm-8" id="nameEn">
            <div class="input-group">
              <span class="input-group-addon" ><b iu-lang="en"></b></span>
              <input class="form-control" required placeholder="Enter title" type="text" name="nameEn"  ng-model="article.nameEn" />
            </div>
          </div>
        </div>

        <div class="form-group" >
          <label for="nameRu" class="col-sm-2 control-label"></label>

          <div class="col-sm-8" id="nameRu">
            <div class="input-group">
              <span class="input-group-addon" ><b iu-lang="ru"></b></span>
              <input class="form-control" required placeholder="Введите название" type="text" name="nameRu"  ng-model="article.nameRu" />
            </div>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-3 col-sm-10">
            <button  class="btn" type="submit"><spring:message code="page.articles.button.save"/></button>
            <a href="#/" class="btn" ><spring:message code="page.articles.button.cancel"/></a>
          </div>
        </div>

      </form>
    </script>

    <script src="${pageContext.request.contextPath}/static/js/angularjs/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/generated/components/administration/article_service.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/private/administration/dictonaries/articles-app.js"></script>

  </tiles:putAttribute>
</tiles:insertDefinition>