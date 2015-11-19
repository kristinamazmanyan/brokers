<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-10 col-md-offset-1">

  <div class="col-md-11 col-md-offset-0">
    </br>
    <label class="checkbox-inline">
      <input type="checkbox" id="Checkbox1" value="option1">Բոլորը
    </label>

    <label class="checkbox-inline">
      <input type="checkbox" id="Checkbox2" value="option2"> 1-ին տեսակ
    </label>

    <label class="checkbox-inline">
      <input type="checkbox" id="Checkbox3" value="option3"> 2-րդ տեսակ
    </label>

    <label class="checkbox-inline">
      <input type="checkbox" id="Checkbox4" value="option4"> 3-րդ տեսակ
    </label>

    <label class="checkbox-inline">
      <input type="checkbox" id="Checkbox5" value="option5"> 4-րդ տեսակ
    </label><br/><br/>
  </div>
  <style type="text/css">
    .table > thead > tr > td.success,
    .table > tbody > tr > td.success,
    .table > tfoot > tr > td.success,
    .table > thead > tr > th.success,
    .table > tbody > tr > th.success,
    .table > tfoot > tr > th.success,
    .table > thead > tr.success > td,
    .table > tbody > tr.success > td,
    .table > tfoot > tr.success > td,
    .table > thead > tr.success > th,
    .table > tbody > tr.success > th,
    .table > tfoot > tr.success > th {
      background-color: rgba(39, 25, 240, 0.05);
    }
    .table-hover > tbody > tr > td.success:hover,
    .table-hover > tbody > tr > th.success:hover,
    .table-hover > tbody > tr.success:hover > td,
    .table-hover > tbody > tr:hover > .success,
    .table-hover > tbody > tr.success:hover > th {
      background-color: rgba(39, 25, 240, 0.07);
    }
  </style>


  <form ng-init="list()">
    <div class="row" >
      <table class="table table-hover">
        <thead>
        <tr>
          <th><spring:message code='page.tasklist.task'/></th>
          <th><spring:message code='page.tasklist.deadline'/></th>
          <th><spring:message code='page.tasklist.description'/></th>

        </tr>
        </thead>

        <tr ng-hide="totalCount === 0" ng-repeat="task in taskList" data-ng-class="{ success: task.seen }">
          <td ><a href="#/view/{{task.id}}">
            <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{task.nameHy}}</span>
            <span  ng-show="'${pageContext.response.locale}' == 'en'">{{task.nameEn}}</span>
            <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{task.nameRu}}</span>
          </a></td>

          <td> {{task.deadline | date : 'hh:mm dd/MM/yyyy  '}} </td>

          <td ><a href="#">
            <span  ng-show="'${pageContext.response.locale}' == 'hy'">{{task.descriptionHy}}</span>
            <span  ng-show="'${pageContext.response.locale}' == 'en'">{{task.descriptionEn}}</span>
            <span  ng-show="'${pageContext.response.locale}' == 'ru'">{{task.descriptionRu}}</span>
          </a></td>
        </tr>

        <tr ng-show="totalCount === 0" >
          <td colspan="3"><div class="well-lg"><spring:message code='page.manage-users.noDataToShow'/></div></td>
        </tr>
      </table>


      <div class="col-md-9 col-md-offset-4">
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
</div>
