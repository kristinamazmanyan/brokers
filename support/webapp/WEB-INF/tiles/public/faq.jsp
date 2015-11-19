<%@ include    file="/WEB-INF/tiles/common/layout/definitions.jsp"%>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page"><spring:message code="page.faq.title"/></tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">

<div class="container col-sm-12">
    <div  class="row"  ng-controller="FAQController">


        <div  class="col-sm-12" ng-show="getManagePermission()"> <a href="#" class="pull-right" ng-click="toggle(0)">add new</a></div>





        <div class="col-sm-12">&nbsp;</div>

      <div  class="row" ng-repeat="todo in todos" >

        <div class="col-sm-12 ">
          <div   ng-show="myV || todo.id === id"  class="panel panel-default">

              <div ng-show="myV && getManagePermission()"  class="btn-toolbar pull-right faq-inline-toolbar" role="toolbar">
                <div class="btn-group" role="group" ><a href="#todo.id" ng-click="gotoUp(todo.id)"class="glyphicon glyphicon-arrow-up"></a></div>
                <div class="btn-group" role="group" ><a href="#todo.id" ng-click="gotoDown(todo.id)"class="glyphicon glyphicon-arrow-down"></a></div>
                <div class="btn-group" role="group" ><a href="#" ng-click="toggle(todo.id)" class="glyphicon glyphicon-edit"></a></div>
                <div class="btn-group" role="group" ><a href="#" ng-click="remove(todo.id)" class="glyphicon glyphicon-remove"></a></div>
              </div>

            <!--Armenian questions/answers panel-->
            <div ng-show="myV && '${pageContext.response.locale}' == 'hy'"   ng-bind-html="convertToHtml(todo.questionHy)" class="panel-heading"></div>
            <div   ng-show="myV && '${pageContext.response.locale}' == 'hy'" ng-bind-html="convertToHtml(todo.answerHy)" class="panel-body"></div>
            <!--Russion questions/answers panel-->
            <div ng-show="myV && '${pageContext.response.locale}' == 'ru'"    ng-bind-html="convertToHtml(todo.questionRu)" class="panel-heading"></div>
            <div   ng-show="myV  && '${pageContext.response.locale}' == 'ru'" ng-bind-html="convertToHtml(todo.answerRu)" class="panel-body"> </div>
            <!--English questions/answers panel-->
            <div ng-show="myV  && '${pageContext.response.locale}' == 'en'"   ng-bind-html="convertToHtml(todo.questionEn)" class="panel-heading"></div>
            <div   ng-show="myV  && '${pageContext.response.locale}' == 'en'" ng-bind-html="convertToHtml(todo.answerEn)" class="panel-body"></div>

            <div ng-show="myVar  && todo.id === id"  class="form-group">
              <form>
                <h4  ng-show="'${pageContext.response.locale}' == 'hy'"class="panel-heading">Հարց</h4>
                <h4 ng-show="'${pageContext.response.locale}' == 'ru'" class="panel-heading">Вопрос</h4>
                <h4 ng-show="'${pageContext.response.locale}' == 'en'" class="panel-heading">Question</h4>
                  <input type="hidden"  ng-model="todo.orderNo" class="form-control" rows="4">
                <lable>Հայերեն</lable>
                  <textarea   ng-model="todo.questionHy" class="form-control" rows="4"> </textarea>
                <lable>Русский</lable>
                <textarea   ng-model="todo.questionRu"class="form-control" rows="4"> </textarea>
                <lable>English</lable>
                <textarea   ng-model="todo.questionEn"class="form-control" rows="4"> </textarea>
                <h4  ng-show="'${pageContext.response.locale}' == 'hy'"class="panel-body">Պատասխան</h4>
                <h4  ng-show="'${pageContext.response.locale}' == 'ru'"class="panel-body">Ответ</h4>
                <h4 ng-show="'${pageContext.response.locale}' == 'en'"class="panel-body">Answer</h4>
                <lable>Հայերեն</lable>
                <textarea   ng-model="todo.answerHy" class="form-control" rows="4"> </textarea>
                <lable>Русский</lable>
                <textarea   ng-model="todo.answerRu" class="form-control" rows="4"> </textarea>
                <lable>English</lable>
                <textarea   ng-model="todo.answerEn" class="form-control" rows="4"> </textarea>
                <div class="col-sm-12">&nbsp;</div>
                  <div class="msg-block" ng-show="myFormError">
                                <span style="color:red" class="msg-error" >
                                     <spring:message  code="profile.page.info.msg"/>
                                </span>
                  </div>
                 <button type="submit" class="btn" ng-click="editTodo(todo.id,todo.orderNo,todo.questionHy ,todo.answerHy,todo.questionRu,todo.answerRu,todo.questionEn,todo.answerEn)">
                  Save changes</button>
                <button type="button" class="btn" ng-click="toggle(todo.id)" >cancel</button>
              </form>

            </div>
          </div>
        </div>
      </div>

      <div ng-show="myVar && id===0">
        <form  >
          <div  class="form-group">
            <h4  ng-show="'${pageContext.response.locale}' == 'hy'"class="panel-heading">Մուտկագրեք Հարցը</h4>
            <h4 ng-show="'${pageContext.response.locale}' == 'ru'" class="panel-heading">Введите Вопрос</h4>
            <h4 ng-show="'${pageContext.response.locale}' == 'en'" class="panel-heading">Input Question</h4>
          </div>
          <div  class="form-group">
            <lable>Հայերեն</lable>
            <textarea   ng-model="todo.questionHy" class="form-control" rows="4"> </textarea></div>
          <div  class="form-group">
            <lable>Русский</lable>
            <textarea   ng-model="todo.questionRu" class="form-control" rows="4"> </textarea></div>
          <div  class="form-group">
            <lable>English</lable>
            <textarea   ng-model="todo.questionEn" class="form-control" rows="4"> </textarea></div>
          <div class="col-sm-12">&nbsp;</div>

          <div  class="form-group">
            <h4  ng-show="'${pageContext.response.locale}' == 'hy'"class="panel-body">Մուտկագրեք  Պատասխանը</h4>
            <h4  ng-show="'${pageContext.response.locale}' == 'ru'"class="panel-body">Введите Ответ</h4>
            <h4 ng-show="'${pageContext.response.locale}' == 'en'"class="panel-body">Input Answer</h4></div>
          <div  class="form-group">
            <lable>Հայերեն</lable>
            <textarea   ng-model="todo.answerHy" class="form-control" rows="4"> </textarea></div>
          <div  class="form-group">
            <lable>Русский</lable>
            <textarea   ng-model="todo.answerRu" class="form-control" rows="4"> </textarea></div>
          <div  class="form-group">
            <lable>English</lable>
            <textarea   ng-model="todo.answerEn" class="form-control" rows="4"> </textarea></div>

          <div class="col-sm-12">&nbsp;</div>
          <a href="#todo.id" >
            <button type="submit" class="btn"  ng-click="addTodo(todo.id,todo.questionHy ,todo.answerHy,todo.questionRu,todo.answerRu,todo.questionEn,todo.answerEn)">
              Save </button></a>
          <button type="button" class="btn" ng-click="toggle(1)" >cancel</button>
        </form>
      </div>
        <div class="msg-block" ng-show="myFormError">
                                <span style="color:red" class="msg-error" >
                                               <spring:message  code="profile.page.info.msg"/>
                                            </span>

        </div>
    </div>

        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/generated/components/informational/faq_service.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/common/components/markDown/markdown.js"></script>
         <script type="application/javascript"
              src="${pageContext.request.contextPath}/static/js/editors/cpv/faq-app.js"></script>






    </div>
  </tiles:putAttribute>
</tiles:insertDefinition>